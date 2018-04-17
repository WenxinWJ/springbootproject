const BANK_URL = "http://127.0.0.1:8086/api/";  // const 常量
const TOKEN_KEY = "jwtToken";

function getJwtToken() {    // 从本地中获取token
    return localStorage.getItem(TOKEN_KEY);
}

function setJwtToken(token) {   // 设置token 到本地
    localStorage.setItem(TOKEN_KEY, token);
}

function removeJwtToken() { // 从本地中删除 token
    localStorage.removeItem(TOKEN_KEY);
}

function createAuthorizationTokenHeader() {
    let token = getJwtToken();
    if (token) {
        return {"Authorization": "Bearer " + token};
    } else {
        return {};
    }
}