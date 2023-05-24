<script setup>
import { ref } from 'vue'
import TheMenu from '../components/Menu.vue'
const AddInfo = ref(false)
</script>
<script>
import axios from 'axios'
import { ref } from 'vue'
import { ElNotification } from 'element-plus'
export default {
    data() {
        return {
            set_img: "https://thirdqq.qlogo.cn/qqapp/101390507/5871CBBE7BD9FBC376875D098EA107C0/100",
            phones: ["non"],
            email: "i@moenya.cat",
            address: "南极洲不知名冰屋",
            u: "http://192.168.1.101:8089",
            username: "",
            na: "",
            ad: "",
            nu: ""
        }
    },
    setup() {
        const na = ref()
        const ad = ref()
        const nu = ref()
        const email = ref()
    },
    mounted() {
        this.LoginCheck()
    },
    methods: {
        LoginOut() {
            localStorage.removeItem("token")
            this.$router.push({
                path:'/login'
            })
        },
        GetAdd() {
            const token=localStorage.getItem("token")
            if (this.na || this.ad){
                axios.put(this.u + "/api/v1/pri/user/base", 
                {
                    "name": this.na,
                    "address": this.ad
                },
                {
                    headers: {
                        token: token
                    }
                }
                ).then((res) => {
                    console.log(res)
                    if (res.data.code === "SUCCESS"){
                        ElNotification({
                            title: '成功',
                            message: '更新成功',
                            type: 'success',
                        })
                        if (this.nu){
                            axios.post(this.u + "/api/v1/pri/user/phone", {
                                "value": this.nu
                            },
                            {
                                headers: {
                                    token: token
                                }
                            }).then((res) => {
                                console.log(res)
                            })
                            
                        }
                        if (this.email){
                            console.log(this.email)
                            axios.post(this.u + "/api/v1/pri/user/mail", {
                                "value": this.email
                            },
                            {
                                headers: {
                                    token: token
                                }
                            }).then((res) => {
                                console.log(res)
                            })
                        }
                        this.$router.go(0)
                    }else
                    {
                        ElNotification({
                            title: '失败',
                            message: '更新失败',
                            type: 'error',
                        })
                    }
                })
            }
            
            
        },
        LoginCheck() {
            const token=localStorage.getItem("token")
            axios.get(this.u + "/api/v1/pri/user", {
                headers: {
                    token: token
                },
            }).then((res) => {
                console.log(res)
                if (res.data.code === 'SUCCESS'){
                    this.username=res.data.data.contact.name
                    this.address=res.data.data.contact.address
                    this.set_img=res.data.data.contact.head
                    this.phones=res.data.data.phones
                    this.email=res.data.data.mails[0].value
                }else{
                    localStorage.removeItem("token")
                    this.$router.push({
                        path:'/login'
                    })
                }
                
            })
        }
    }
}
</script>

<style scoped>
.person-img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  overflow: hidden; /* 隐藏超出边界部分 */
  position: relative;
  top:20px;
  left: 50%;
  transform: translate(-55%);
}
.centered-image {
    display: block; /* 将图片设置为块级元素 */
    /* 使用flex布局使图片水平垂直居中 */
  	display: flex;
  	justify-content:center;
  	align-items:center;
	height:auto;/*保证按比例缩放*/
	width:auto;/*保证按比例缩放*/
	max-height:100%;/*限制最大高度*/
	max-width :100%;/*限制最大宽度*/
}

.person-top{
    position: relative;
    top:0px;
    height: 50px;
}

.person-name{
    text-align: center;
    position: relative;
    top: 30px;
    font-weight: 800;
    font-size: 25px;
}

.person-phone-box{
    position: relative;
    top: 0px;
    height:100px;
}

.person-phone{
    width: 85%;
    height:40px;
    margin-top: 10px;
    background-color: rgba(255, 255, 255, 0.911);
    position: relative;
    left:50%;
    transform: translate(-50%);
}

.person-email{
    width: 85%;
    height:40px;
    margin-top: 10px;
    background-color: rgba(255, 255, 255, 0.911);
    position: relative;
    left:50%;
    transform: translate(-50%);
}

.phoneicon{
    background: url("../assets/phone.svg") no-repeat;
    background-size: 20px 20px;
    background-position: center center;
    height: 20px;
    width: 20px;
    display: inline-block;
    position:relative;
    left:20%;
    top:10px;
}

.emailicon{
    background: url("../assets/email.svg") no-repeat;
    background-size: 20px 20px;
    background-position: center center;
    height: 20px;
    width: 20px;
    display: inline-block;
    position:relative;
    left:20%;
    top:10px;
}

.phonenum{
    font-size: 17px;
    font-weight: 500;
    position:relative;
    left:30%;
    top:5px;
}

.email{
    position: relative;
    top:0px;
}

.person-adress{
    width: 85%;
    height:40px;
    margin-top: 10px;
    background-color: rgba(255, 255, 255, 0.911);
    position: relative;
    left:50%;
    transform: translate(-50%);
}

.adressicon{
    background: url("../assets/adress.svg") no-repeat;
    background-size: 20px 20px;
    background-position: center center;
    height: 20px;
    width: 20px;
    display: inline-block;
    position:relative;
    left:20%;
    top:10px;
}

.myinfo{
    font-weight: 700;
    float:left;
    margin-left: 10px;
    margin-top:5px;
    font-size: 20px;
}

.head-icon{
    float: right;
}

.loginout{
    background: url("../assets/log-in-outline.svg") no-repeat;
    background-size: 30px 30px;
    background-position: center center;
    height: 30px;
    width: 30px;
    display: inline-block;
    position: relative;
    top:5px;
    right: 7%;
}

.resetinfo{
    background: url("../assets/add.svg") no-repeat;
    background-size: 30px 30px;
    background-position: center center;
    height: 30px;
    width: 30px;
    display: inline-block;
    position: relative;
    top:5px;
    right: 10%;
}

.infobox{
    width: 100%;
    position: relative;
    top:30px;
    height: 350px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>

<template>
    <div>
        <div class="person-top">
            <span class="myinfo">我的信息</span>
            <i class="head-icon loginout" @click=LoginOut></i>
            <i class="head-icon resetinfo" @click="AddInfo = true"></i>
        </div>
        <el-dialog v-model="AddInfo" title="更改信息" width="90%" center>
            <span>更改昵称</span>
            <el-input v-model="na" placeholder="请输入昵称" />
            <span>更改地址</span>
            <el-input v-model="ad" placeholder="请输入地址" />
            <span>增加手机号</span>
            <el-input v-model="nu" placeholder="请输入手机号" />
            <span>修改邮箱</span>
            <el-input v-model="email" placeholder="请输入邮箱地址" />
            <template #footer>
            <span class="dialog-footer">
                <el-button @click="AddInfo = false">取消</el-button>
                <el-button type="primary" @click="AddInfo = false, GetAdd()">确认</el-button>
            </span>
            </template>
        </el-dialog>
        <div class="person-img">
            <img :src="set_img" alt="image" class="centered-image">
        </div>
        <h3 class="person-name">{{ username }}</h3>
        <div class="infobox">
            <el-scrollbar height="80%">
                <div class="person-phone-box">
                    <el-scrollbar height="100px">
                        <div v-for="p in phones" :key="p" class="person-phone">
                            <i class="phoneicon"></i>
                            <span class = "phonenum">{{ p.value }}</span>
                        </div>
                    </el-scrollbar>
                </div>
                <div class="person-email email">
                    <i class="emailicon"></i>
                    <span class = "phonenum">{{ email }}</span>
                </div>
                <div class="person-adress">
                    <i class="adressicon"></i>
                    <span class = "phonenum">{{ address }}</span>
                </div>
            </el-scrollbar>
        </div>
        <TheMenu />
    </div>
</template>