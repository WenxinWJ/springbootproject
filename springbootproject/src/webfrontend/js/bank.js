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

/**
 * 快速将 form表单数据 转换成json 格式
 */
function getFormData($form) {
    let unindexed_array = $form.serializeArray();
    let indexed_array = {};

    $.map(unindexed_array, (n, i) => {
        indexed_array[n['name']] = n['value']
    });
    return indexed_array
}