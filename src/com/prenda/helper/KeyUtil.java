package com.prenda.helper;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import com.prenda.servlet.RegisterOwner;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

public class KeyUtil {

	public String getJws() {
		String jws="";
		try {
			Properties props = new Properties();
			props.load(RegisterOwner.class.getResourceAsStream("/env.properties"));
			// String token = props.getProperty("github.token");
			String path = props.getProperty("github.pem");
			GregorianCalendar gc = new GregorianCalendar();
			gc.add(GregorianCalendar.MINUTE, 10);
			jws = Jwts.builder().setIssuer("10575").setIssuedAt(new Date()).setExpiration(gc.getTime())
					.signWith(SignatureAlgorithm.RS256, getPemPrivateKey(path, "RSA")).compact();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jws;
	}

	public RSAPrivateKey getPemPrivateKey(String filename, String algorithm) {
		RSAPrivateKey rSAPrivateKey = null;
		try {
			File f = new File(filename);
			FileInputStream fis = new FileInputStream(f);
			DataInputStream dis = new DataInputStream(fis);
			byte[] keyBytes = new byte[(int) f.length()];
			dis.readFully(keyBytes);
			dis.close();

			String temp = new String(keyBytes);
			String privKeyPEM = temp.replace("-----BEGIN PRIVATE KEY-----\n", "");
			privKeyPEM = privKeyPEM.replace("-----END PRIVATE KEY-----", "");
			// System.out.println("Private key\n"+privKeyPEM);

			byte[] decoded = Base64.getDecoder().decode(privKeyPEM); // TextCodec.BASE64.decode(privKeyPEM); //

			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
			KeyFactory kf = KeyFactory.getInstance(algorithm);
			rSAPrivateKey = (RSAPrivateKey) kf.generatePrivate(spec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSAPrivateKey;
	}
}
