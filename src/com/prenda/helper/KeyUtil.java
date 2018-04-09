package com.prenda.helper;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.GregorianCalendar;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import com.prenda.Mode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class KeyUtil {
	
	private static Logger log = Logger.getLogger(KeyUtil.class);
	
	public static String getJws() {
		String jws = "";
		try {
			Properties props = new Properties();
			props.load(KeyUtil.class.getResourceAsStream("/env.properties"));
			String path = props.getProperty("github.pem");
			String issuer = props.getProperty("github.app");
			Path p = Paths.get(path);
			if(Files.notExists(p)) {
				download(p);
			}
			GregorianCalendar gc = new GregorianCalendar();
			long iat = gc.getTimeInMillis()/1000;
			log.info("iat " + iat);
			log.info("exp " + (iat + 60 * 10));
			Claims claims = Jwts.claims();
			claims.put("iat", iat);
			claims.put("exp", (iat + 60 * 10));
			JwtBuilder builder = Jwts.builder(); //JWTBuilder iat and exp are different format for Github
			builder = builder.setClaims(claims);
			builder = builder.setIssuer(issuer);
			log.info("claims: " + claims.toString());
			jws = builder.signWith(SignatureAlgorithm.RS256, getPemPrivateKey(path, Mode.PKCS1)).compact();
			log.info("jws: "+ jws);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jws;
	}

	private static void download(Path path) {
		try {
			Properties props = new Properties();
			props.load(KeyUtil.class.getResourceAsStream("/env.properties"));
			String request = props.getProperty("github.pemUrl");
			log.info(request);
			URL url = new URL(request);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			log.info("Response Code : " + responseCode);
			if(responseCode == 200) {
				InputStream in = conn.getInputStream();
				OutputStream outputStream = new FileOutputStream(path.toString());
				byte[] buffer = new byte[2048];
				int length;
				while ((length = in.read(buffer)) != -1) {
					outputStream.write(buffer, 0, length);
				}
				in.close();
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Key getPemPrivateKey(String filename, int pkcs) { 
		Key key = null;
		try {
			File f = new File(filename);
			FileInputStream fis = new FileInputStream(f);
			DataInputStream dis = new DataInputStream(fis);
			byte[] keyBytes = new byte[(int) f.length()];
			dis.readFully(keyBytes);
			dis.close();
			String temp = new String(keyBytes);
			String privKeyPEM ="";
			if(pkcs == Mode.PKCS8) {
				privKeyPEM = temp.replace("-----BEGIN PRIVATE KEY-----\n", "");
				privKeyPEM = privKeyPEM.replace("-----END PRIVATE KEY-----", "");
				log.info("Private key\n"+privKeyPEM);
				byte[] decoded = Base64.getDecoder().decode(privKeyPEM); // TextCodec.BASE64.decode(privKeyPEM); //
				PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
				KeyFactory kf = KeyFactory.getInstance("RSA");
				key = (Key) kf.generatePrivate(spec);
			}else if (pkcs == Mode.PKCS1) {
				PEMParser pemParser = new PEMParser(new FileReader(filename));
				JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider(new BouncyCastleProvider());
				Object object = pemParser.readObject();
				KeyPair kp = converter.getKeyPair((PEMKeyPair) object);
				key = (Key) kp.getPrivate();
				pemParser.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}
}
