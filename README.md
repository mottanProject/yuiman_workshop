# yuiman_workshop

## docker composeの使い方
1. cd docker
2. dockerディレクトリにcdしたら、PCでdockerを起動している状態でdocker-compose up -d
3. docker psでpostgres_serverが起動しているのを確認
4. docker exec -it postgres_server bashでコンテナに入る
5. postgreにログインする
