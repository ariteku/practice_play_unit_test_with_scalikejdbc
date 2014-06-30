unit testをおためし
========================================

```
mysql -uroot test_scalikejdbc < etc/build.sql
```

```
play test
play "test-only models.generate.UserSpec"
```
