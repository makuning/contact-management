<script>
import { ref } from 'vue'
import { ElNotification } from 'element-plus'
import axios from 'axios'
export default {
    data() {
        return {
            username: "",
            password: "",
            loginstatus: 0,
            u: "http://192.168.1.100:8089"
        }
    },
    setup() {
        const username = ref()
        const password = ref()
    },
    methods: {
        LoginClick() {
            this.$data.loginstatus = 'loading';
            if (this.username === '' || this.password === ''){
                ElNotification({
                    title: '错误',
                    message: '账号密码不能为空',
                    type: 'error',
                })
                this.$data.loginstatus = 0;
            }
            else{
                axios.post(this.u + "/api/v1/pri/user/login", {
                    username: this.username,
                    password: this.password
                }).then((res) => {
                    console.log(res)
                    if (res.data.code == 'SUCCESS'){
                        ElNotification({
                            title: '成功',
                            message: '登陆成功',
                            type: 'success',
                        })
                        let token = res.data.data
                        localStorage.setItem("token", token)
                        this.$router.push({
                            path:'/'
                        })
                    }
                    else{
                        ElNotification({
                        title: '错误',
                        message: '登陆失败',
                        type: 'error',
                        })
                        this.$data.loginstatus = 0;
                    }
                })
            }
        },
        resClick() {
            this.$router.push({
                path:'/register'
            })
        },
    }
}
    
</script>
<style>
.login-div {
  margin:0px;
  padding:0px;
  width:80%;
  position: relative;
  left:50%;
  top:50%;
  transform: translate(-60%, -50%);
}
.login-card {

  background-color: rgba(240, 248, 255, 0);
  margin:0px;
  padding:0px;
  
}
.login-title {
  text-align: center;
  margin-left: 20%;
}
.login-form {
    padding: 0px;
    margin:0;
    width:80%;
    position: relative;
    top:10px;
    left:50%;
    transform: translate(-35%);
}

.login-btn{
    margin-top: 10px;
    position: relative;
    left:40%;
    transform: translate(-70%);
}

.login-input{
    margin-top:10px;
}

.login-icon{
    background: url("../assets/cat.svg") no-repeat;
    background-image: "../assets/cat.svg";
    background-size: 100% 100%;
    background-position: center center;
    height: 100px;
    width: 100px;
    display: inline-block;
    position:relative;
    left:50%;
    transform: translate(-20%);
    bottom:0;
    
}
</style>

<template>
    <div class="login-div">
        <div class="login-card">
            <div class="login-icon"></div>
            <h2 class="login-title">登入</h2>
            <div class="login-form">
                <el-input class="login-input" v-model="username" placeholder="请输入账号" />
                <el-input class="login-input" v-model="password" type="password" placeholder="请输入密码" show-password />
                <el-button class="login-btn" type="primary" :loading-icon="Eleme" @click=LoginClick   :loading="loginstatus">Go!</el-button>
                <el-button class="login-btn" type="primary" :loading-icon="Eleme" @click=resClick>注册</el-button>
            </div>
        </div>
    </div>
</template>

