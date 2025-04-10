# kafka-scalability-sandbox
kafka-scalability-sandbox

# 준비
- ```base
  # 개발용 도커 컨테이너 띄우기
  docker compose -f ./docker-compose-dev.yml up
  
  # 그래들 빌드 진행
  ./gradlew clean build
  
  # 개발용 도커 컨테이너 끄기
  docker compose -f ./docker-compose-dev.yml down
  
  # k6 테스트 용 도커 컨테이너 띄우기
  docker compose up
  
  ## - 카프카 토픽 생성
  ## - k6 테스트 진행
    ```
  
## 토픽 생성
- ```bash
    kafka-topics.sh --create \
    --bootstrap-server kafka:9092 \
    --topic order2 \
    --partitions 1 \
    --replication-factor 1 \
    --config retention.ms=600000 \
    --config segment.ms=600000 \
    --config delete.retention.ms=120000 \
    --config compression.type=zstd \
    --config min.insync.replicas=1 \
    --config max.message.bytes=262144 \
    --config flush.messages=1 \
    --config flush.ms=1000

    kafka-topics.sh --create \
    --bootstrap-server kafka:9092 \
    --topic order3 \
    --partitions 3 \
    --replication-factor 1 \
    --config retention.ms=600000 \
    --config segment.ms=600000 \
    --config delete.retention.ms=120000 \
    --config compression.type=zstd \
    --config min.insync.replicas=1 \
    --config max.message.bytes=262144 \
    --config flush.messages=1 \
    --config flush.ms=1000

    kafka-topics.sh --create \
    --bootstrap-server kafka:9092 \
    --topic inventory2 \
    --partitions 3 \
    --replication-factor 1 \
    --config retention.ms=600000 \
    --config segment.ms=600000 \
    --config delete.retention.ms=120000 \
    --config compression.type=zstd \
    --config min.insync.replicas=1 \
    --config max.message.bytes=262144 \
    --config flush.messages=1 \
    --config flush.ms=1000

    kafka-topics.sh --create \
    --bootstrap-server kafka:9092 \
    --topic inventory3 \
    --partitions 3 \
    --replication-factor 1 \
    --config retention.ms=600000 \
    --config segment.ms=600000 \
    --config delete.retention.ms=120000 \
    --config compression.type=zstd \
    --config min.insync.replicas=1 \
    --config max.message.bytes=262144 \
    --config flush.messages=1 \
    --config flush.ms=1000

    kafka-topics.sh --create \
    --bootstrap-server kafka:9092 \
    --topic inventory4 \
    --partitions 3 \
    --replication-factor 1 \
    --config retention.ms=600000 \
    --config segment.ms=600000 \
    --config delete.retention.ms=120000 \
    --config compression.type=zstd \
    --config min.insync.replicas=1 \
    --config max.message.bytes=262144 \
    --config flush.messages=1 \
    --config flush.ms=1000
    ```
  
## k6 테스트
- ```bash
    cat ./k6/1-script-spring-update.js | docker run --volume "./k6/k6-result:/k6-result" --network  kafka-scalability-sandbox_docker_net --name k6 --rm -i grafana/k6 run -
  
    cat ./k6/2-script-kafka-partition-1-update.js | docker run --volume "./k6/k6-result:/k6-result" --network  kafka-scalability-sandbox_docker_net --name k6 --rm -i grafana/k6 run -
  
    cat ./k6/3-script-kafka-partition-3-update.js | docker run --volume "./k6/k6-result:/k6-result" --network  kafka-scalability-sandbox_docker_net --name k6 --rm -i grafana/k6 run -
  
    cat ./k6/4-script-spring-insert.js | docker run --volume "./k6/k6-result:/k6-result" --network  kafka-scalability-sandbox_docker_net --name k6 --rm -i grafana/k6 run -
  
    cat ./k6/5-script-kafka-partition-1-insert.js | docker run --volume "./k6/k6-result:/k6-result" --network  kafka-scalability-sandbox_docker_net --name k6 --rm -i grafana/k6 run -
  
    cat ./k6/6-script-kafka-partition-3-insert.js | docker run --volume "./k6/k6-result:/k6-result" --network  kafka-scalability-sandbox_docker_net --name k6 --rm -i grafana/k6 run -
  
    cat ./k6/7-script-kafka-partition-3-batch-insert.js | docker run --volume "./k6/k6-result:/k6-result" --network  kafka-scalability-sandbox_docker_net --name k6 --rm -i grafana/k6 run -
    ```
  
## 시나리오 1,2,3에서 딜레이적용
- 설정 변경
  - ./src/main/kotlin/com/example/demo/config/AppConfig.kt 열기
  - USE_DELAY 를 true 로 변경
  - 스프링 빌드 후 도커 재실행

## 결과 확인
- ```bash
    # 시나리오 1,2,3 결과 조회
    curl "http://localhost:8080/orders"
  
    # 시나리오 4,5,6,7 결과 조회
    curl "http://localhost:8080/inventories"
    ```
- k6 결과 위치
  - ./k6/k6-result
    