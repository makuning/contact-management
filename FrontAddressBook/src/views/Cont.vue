<script setup>
import { ref } from 'vue'
import TheMenu from '../components/Menu.vue'
const AddInfo = ref(false)
</script>
<script>
import axios from 'axios'
export default {
    data() {
        return {
            contact: {},
            contactnum: 0,
            set_img: "https://thirdqq.qlogo.cn/qqapp/101390507/5871CBBE7BD9FBC376875D098EA107C0/100",
            u: "http://192.168.1.101:8089",
        }
    },
    setup() {
        
    },
    mounted() {
        this.LoginCheck()
    },
    methods: {
        LoginCheck() {
            const token=localStorage.getItem("token")
            axios.get(this.u + "/api/v1/pri/contact", {
                headers: {
                    token: token
                },
            }).then((res) => {
                console.log(res.data.data[0])
                if (res.data.code==='SUCCESS'){
                    this.contactnum=res.data.data.length
                    this.contact=res.data.data
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
.head-icon{
    float: right;
}
.search{
    background: url("../assets/search.svg") no-repeat;
    background-size: 30px 30px;
    background-position: center center;
    height: 30px;
    width: 30px;
    display: inline-block;
    position: relative;
    top:5px;
    right: 10%;
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
    right: 7%;
}

.myinfo{
    font-weight: 700;
    float:left;
    margin-left: 10px;
    margin-top:5px;
    font-size: 20px;
}

.contact-box{
    width: 95%;
    position: relative;
    top:50px;
    left:50%;
    transform: translate(-50%);
}

.contact-info{
    width:95%;
    height:100px;
    margin-top: 10px;
}

.contact-img{
    float: left;
    margin-left: 10px;
    height: 100px;
    width:100px;
    border-radius: 50%;
    overflow: hidden; /* 隐藏超出边界部分 */
}

.centered-image{
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

.contact-info-name{
    font-size:20px;
    font-weight: 600;
    position: relative;
    left: 15px;
}

.contact-info-num{
    font-size: 17px;
    font-weight: 400;
    position: relative;
    left: 15px;
    line-height: 20px;
}
</style>

<template>
    <div>
        <div class="person-top">
            <span class="myinfo">{{ contactnum }}个联系人</span>
            <i class="head-icon resetinfo" @click="AddInfo = true"></i>
            <i class="head-icon search"></i>
        </div>
        <el-dialog v-model="AddInfo" title="更改信息" width="90%" center>
            <span>
            用于更新联系人信息
            </span>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="AddInfo = false">取消</el-button>
                    <el-button type="primary" @click="AddInfo = false">
                    确认
                    </el-button>
                </span>
            </template>
        </el-dialog>
        <el-scrollbar height="400px" class="contact-box">
            <div v-for="p in contact" :key="p" class="contact-info">
                <div class="contact-img">
                    <img :src="p.contact.head" alt="image" class="centered-image">
                </div>
                <p class = "contact-info-name">{{ p.contact.name }}</p>
                <p class = "contact-info-num">{{ p.phones[0].value }}</p>
                <p class = "contact-info-num">{{ p.mails[0].value }}</p>
                <p class = "contact-info-num">{{ p.contact.address }}</p>
            </div>
        </el-scrollbar>
        <TheMenu />
    </div>
</template>>