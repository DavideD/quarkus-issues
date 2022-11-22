A test project for https://github.com/hibernate/hibernate-reactive/issues/1427

Examples using [HTTPie](https://httpie.io/) for rest calls:
```
gradle quarkusDev
```

List entities:
```
ttp GET localhost:8080/hello
```

Create a new entity:
```
http POST localhost:8080/hello transactionMessageId=Uno inboundTransaction=innbound createdUserId=3
```

Update the entity:
```
http PUT localhost:8080/hello/Uno inboundTransaction=NEW_INBOUND createdUserId=3  
```
