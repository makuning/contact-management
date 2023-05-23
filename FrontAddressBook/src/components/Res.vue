<script>
import { ref } from 'vue'
import axios from 'axios'
import { ElNotification } from 'element-plus'
export default {
    data() {
        return {
            username: "",
            password: "",
            u: "http://192.168.1.100:8089"
        }
    },
    setup() {
        const username = ref()
        const password = ref()
    },
    methods: {
        resClick() {
            if (this.username === '' || this.password === ''){
                ElNotification({
                    title: '错误',
                    message: '账号密码不能为空',
                    type: 'error',
                })
            }
            else{
                axios.post(this.u + "/api/v1/pri/user/register", {
                    username: this.username,
                    password: this.password
                }).then((res) => {
                    if (res.data.code == 'SUCCESS'){
                        ElNotification({
                            title: '成功',
                            message: '注册成功',
                            type: 'success',
                        })
                        this.$router.push({
                            path:'/login'
                        })
                    }
                    else{
                        ElNotification({
                        title: '错误',
                        message: '注册失败',
                        type: 'error',
                        })
                    }
                })
            }
        },
        backloginClick() {
            this.$router.push({
                path: '/login'
            })
        }
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
    left:35%;
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

.btn2{
    position: relative;
    left:45%;
}
</style>

<template>
    <div class="login-div">
        <div class="login-card">
            <i class="login-icon"></i>
            <h2 class="login-title">注册</h2>
            <div class="login-form">
                <el-input class="login-input" v-model="username" placeholder="请输入账号" />
                <el-input class="login-input" v-model="password" type="password" placeholder="请输入密码" show-password />
                <el-button class="login-btn" type="primary" :loading-icon="Eleme" @click=resClick>注册</el-button>
                <el-button class="login-btn btn2" type="primary" :loading-icon="Eleme" @click=backloginClick>返回登录</el-button>
            </div>
        </div>
    </div>
</template>

