<script setup>
import { ref } from 'vue'
import TheMenu from '../components/Menu.vue'
const AddInfo = ref(false)
</script>
<script>
export default {
    data() {
        return {
            contact: [{name:"张三", phonenum: 123, email:"110@110.com", url:"../assets/adress.svg"},{name:"张三", phonenum: 123, email:"110@110.com", url:"../assets/adress.svg"}],
            contactnum: 0
        }
    },
    setup() {
        
    },
    mounted() {
        
    },
    methods: {

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
            用于更新个人信息
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
        <TheMenu />
    </div>
</template>>