# Wechat payment by java
### server: aws ec2
### ide: eclipse + spring boot


# LETSENCRYPT COMMAND
openssl pkcs12 -export -in fullchain.pem -inkey privkey.pem -out jchen_letsencrypt.p12 -name tomcat_letsencrypt

# GEN JKS CERT
keytool -importkeystore -deststorepass 'xxx' -destkeypass 'xxx' -destkeystore jchen_letsencrypt.jks -srckeystore jchen_letsencrypt.p12 -srcstoretype PKCS12 -srcstorepass 'xxx' -alias tomcat_letsencrypt

