package com.prenda.helper;

import java.io.FileReader;
import java.security.Key;
import java.security.KeyPair;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import com.prenda.servlet.RegisterOwner;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class KeyUtil {
	
	private static Logger log = Logger.getLogger(KeyUtil.class);

	public String getJws() {
		String jws="";
		try {
			Properties props = new Properties();
			props.load(RegisterOwner.class.getResourceAsStream("/env.properties"));
			String path = props.getProperty("github.pem");
			GregorianCalendar gc = new GregorianCalendar();
			gc.add(GregorianCalendar.MINUTE, 10);
			jws = Jwts.builder().setIssuer("10575").setIssuedAt(new Date()).setExpiration(gc.getTime())
					.signWith(SignatureAlgorithm.RS256, getPemPrivateKey(path, "BC")).compact();
			log.info("jws: "+ jws);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jws;
	}

	public Key getPemPrivateKey(String filename, String provider) { 
		Key key = null;
		try {
			/* PKCS8
			File f = new File(filename);
			FileInputStream fis = new FileInputStream(f);
			DataInputStream dis = new DataInputStream(fis);
			byte[] keyBytes = new byte[(int) f.length()];
			dis.readFully(keyBytes);
			dis.close();

			String temp = new String(keyBytes);
			String privKeyPEM = temp.replace("-----BEGIN RSA PRIVATE KEY-----\n", ""); //PCKS1 is -----BEGIN PRIVATE KEY-----\n
			privKeyPEM = privKeyPEM.replace("-----END RSA PRIVATE KEY-----", ""); // //PCKS1 is -----END PRIVATE KEY-----
			log.info("Private key\n"+privKeyPEM);

			byte[] decoded = Base64.getDecoder().decode(privKeyPEM); // TextCodec.BASE64.decode(privKeyPEM); //

			/*PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
			KeyFactory kf = KeyFactory.getInstance(algorithm);
			rSAPrivateKey = (RSAPrivateKey) kf.generatePrivate(spec);*/
			
			PEMParser pemParser = new PEMParser(new FileReader(filename));
			JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider(provider);
			Object object = pemParser.readObject();
			KeyPair kp = converter.getKeyPair((PEMKeyPair) object);
			key = (Key) kp.getPrivate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}
}
