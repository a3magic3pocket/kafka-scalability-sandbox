import http from 'k6/http'
import { sleep, check } from 'k6'

const typeName = "2-script-kafka-partition-1-update"

export const options = {
    vus: 1000,
    duration: '5m',
    iterations: 3000,
}

export function setup() {
    const url = 'http://web:8080/order2s';
    const payload = JSON.stringify({
        userName: 'kim',
        productName: 'product',
        quantity: 1
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    let res = http.post(url, payload, params);
    console.log('Initial POST status:', res.status);
    if (res.status >= 400) {
        console.error(`❌ Initial POST failed. Status: ${res.status}, Body: ${res.body}`);
        throw new Error(`POST failed with status ${res.status}`);
    }

    const body = JSON.parse(res.body);
    const orderId = body.id;

    return { orderId };
}

export default function (data) {
    const url = `http://web:8080/order2s/${data.orderId}`

    const payload = JSON.stringify({
        userName: 'kim',
        productName: 'product',
        quantity: 1
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    let res = http.put(url, payload, params);
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