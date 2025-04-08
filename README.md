# kafka-scalability-sandbox
kafka-scalability-sandbox

# 최초 세팅
- ```base
  # 도커 컨테이너 띄우기
  docker compose up -d
  
  ## DB 생성
  # DB 도커 컨테이너 쉡 접속
  docker exec -it db bash
  
  # DB 쉡 접속
  mariadb -u root -p
  
  # DB 생성
  # 생성 후 Ctrl + D로 나가기
  CREATE DATABASE experiment;

  # order upsert 예시 
  curl -X POST --location 'http://localhost:8080/orders/1/upsert' --header 'Content-Type: application/json' --data '{"userName": "kim", "productName": "product", "quantity": 1}'
    ```
  
## k6 테스트
- ```bash
    cat ./k6/1-script-spring.js | docker run --volume "./k6/k6-result:/k6-result" --network  kafka-scalability-sandbox_docker_net --name k6 --rm -i grafana/k6 run -

    ```