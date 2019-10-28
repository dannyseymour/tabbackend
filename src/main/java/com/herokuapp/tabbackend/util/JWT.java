package com.herokuapp.tabbackend.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Scanner;


public class JWT {

  public static String getJWTToken(String userName, String apiKey, String privateKeyString, int validityOfTokenInSecs) throws JoseException {

    JwtClaims claims = new JwtClaims();
    claims.setIssuer(apiKey);
    NumericDate issuedDate=NumericDate.now();
    issuedDate.addSeconds(-150);
    claims.setIssuedAt(issuedDate);

    NumericDate tokenExpDate = NumericDate.now();
    tokenExpDate.addSeconds(validityOfTokenInSecs);
    claims.setExpirationTime(tokenExpDate);

    if (userName != null && !userName.isEmpty())
      claims.setClaim("sub", userName);


    PrivateKey privateKey = null;

    try {
      Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
      byte[] content = privateKeyString.getBytes();

      String pkcs8Pem = new String(content, StandardCharsets.UTF_8);
      byte[] pkcs8EncodedBytes = org.apache.commons.codec.binary.Base64.decodeBase64(pkcs8Pem);
      KeyFactory factory = KeyFactory.getInstance("RSA");
      PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);
      privateKey = factory.generatePrivate(privKeySpec);

    }  catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
    }

    JsonWebSignature jws = new JsonWebSignature();
    jws.setKey(privateKey);
    jws.setPayload(claims.toJson());
    jws.setHeader("typ", "JWT");
    jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA512);// Setting the algorithm to be used
    String signedJwt = jws.getCompactSerialization();// payload is signed using this compactSerialization

    return signedJwt;
  }


  public String readPrivKey(String fileName) {

    StringBuilder result = new StringBuilder("");

    //Get file from resources folder
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource(fileName).getFile());

    try (Scanner scanner = new Scanner(file)) {

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if((!line.contains("BEGIN")) && (!line.contains("END"))) {
          result.append(line).append("\n");
        }
      }

      scanner.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

    return result.toString();

  }





}



