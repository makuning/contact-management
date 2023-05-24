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
            u: "http://192.168.1.101:8089",
            groupnum: 0,
            grouplist:{},
            groupname:"",
            contact: {},
            gn1:"",
            gn2:""
        }
    },
    setup() {
        const groupname = ref()
        const gn1=ref()
        const gn2=ref()
    },
    mounted() {
        this.LoginCheck()
    },
    methods: {
        LoginCheck() {
            const token=localStorage.getItem("token")
            axios.get(this.u + "/api/v1/pri/group", {
                headers: {
                    token: token
                },
            }).then((res) => {
                console.log(res)
                this.groupnum=res.data.data.length
                this.grouplist=res.data.data
            })
        },
        Searchcontact() {
            console.log(this.groupname)
            const token=localStorage.getItem("token")
            axios.get(this.u + "/api/v1/pri/contact/group/" + this.groupname, {
                headers: {
                    token: token
                },
            }).then((res) => {
                console.log(res)
                this.contact=res.data.data
            })
        },
        LoadInfo() {
            const token=localStorage.getItem("token")
            if (this.gn1){
                axios.post(this.u + "/api/v1/pri/group", 
                {
                    "name": this.gn1
                },
                {
                    headers:{
                        token: token
                    }
                }
                ).then((res) => {
                    console.log(res)
                    if (res.data.code === "SUCCESS"){
                        ElNotification({
                            title: '成功',
                            message: '创建成功',
                            type: 'success',
                        })
                        this.$router.go(0)
                    }else{
                        ElNotification({
                            title: '失败',
                            message: '创建失败',
                            type: 'error',
                        })
                    }
                })
            }
            if (this.gn2){
                
            }
        }
    }
}
</script>


<style scoped>
.group-box{
    width: 95%;
    position: relative;
    top:50px;
    left:50%;
    transform: translate(-50%);
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

.group-clo{
    background-color: rgb(248, 248, 240);
}

.title-font{
    background-color: rgb(248, 248, 240);
}

::v-deep .el-collapse-item__header{
    font-weight: 500;
    font-size: 16px;
    background-color: rgb(255, 255, 255);
    padding-left:10px;
    border:none;
}

::v-deep .el-collapse-item__content{
    background-color: rgb(248, 248, 240);
    border:none;
}

::v-deep .el-collapse-item__wrap{
    border:none;
}

::v-deep .el-collapse{
    border:none;
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

::v-deep .el-collapse-item__wrap{
    background-color: rgb(248, 248, 240);
}
</style>

<template>
    <div>
        <div class="person-top">
            <span class="myinfo">{{ groupnum }}个群组</span>
            <i class="head-icon resetinfo" @click="AddInfo = true"></i>
            <i class="head-icon search"></i>
        </div>
        <el-dialog v-model="AddInfo" title="更新群组信息" width="90%" center>
            <span>添加群组</span>
            <el-input v-model="gn1" placeholder="请输入群组名" />
            <span>删除群组</span>
            <el-input v-model="gn2" placeholder="请输入群组名" />
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="AddInfo = false">取消</el-button>
                    <el-button type="primary" @click="AddInfo = false, LoadInfo()">
                    确认
                    </el-button>
                </span>
            </template>
        </el-dialog>
        <el-scrollbar height="400px" class="group-box">
            <div>
                <el-collapse v-for="(p, index) in grouplist" :key="p" class="group-clo" v-model="activeName" accordion>
                    <el-collapse-item @click="groupname=p.id, Searchcontact()" class="title-font" :title="p.name" :name="index">
                        <div v-for="p in contact" :key="p" class="contact-info">
                            <div class="contact-img">
                                <img :src="p.contact.head" alt="image" class="centered-image">
                            </div>
                            <p class = "contact-info-name">{{ p.contact.name }}</p>
                            <p class = "contact-info-num">{{ p.phones[0].value }}</p>
                            <p class = "contact-info-num">{{ p.mails[0].value }}</p>
                            <p class = "contact-info-num">{{ p.contact.address }}</p>
                        </div>
                    </el-collapse-item>
                </el-collapse>
            </div>
        </el-scrollbar>
    </div>
        
    <TheMenu />
</template>