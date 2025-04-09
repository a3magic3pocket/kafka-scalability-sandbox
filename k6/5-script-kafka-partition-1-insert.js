import http from 'k6/http'
import { sleep, check } from 'k6'

const typeName = "5-script-kafka-partition-1-insert"

export const options = {
    vus: 1000,
    duration: '5m',
    iterations: 3000,
}

export default function () {
    const url = 'http://web:8080/inventory2s';

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    let res = http.post(url, {}, params);
}

export function handleSummary(data) {
    const summary = {
        http_req_failed: data.metrics.http_req_failed,
        http_reqs: data.metrics.http_reqs,
        state: data.state,
        http_req_duration: data.metrics.http_req_duration,
    };

    return {
        'stdout': JSON.stringify(data, null, 2),
        [`/k6-result/${typeName}.json`]: JSON.stringify(summary, null, 2),
    };
}