<script>
export default {
    data() {
        return {
            
        }
    },
    setup() {
        
    },
    mounted() {
    },
    methods: {
        GoPerson() {
            this.$router.push({
                path:'/'
            })
        },
        GoContact() {
            this.$router.push({
                path:'/cont'
            })
        },
        GoGroup() {
            this.$router.push({
                path:'/group'
            })
        }
    }
}
</script>


<style scoped>
.menu{
    text-align: center;
    position: absolute;
    bottom: 0px;
    width:100%;
    height:70px;
}

.menuicon-group{
    background: url("../assets/group.svg") no-repeat;
    background-size: 30px 30px;
    background-position: center center;
    height: 30px;
    width: 30px;
    display: inline-block;
    position:relative;
    left:0px;
}

.menuicon-contact{
    background: url("../assets/contacts-line.svg") no-repeat;
    background-size: 30px 30px;
    background-position: center center;
    height: 30px;
    width: 30px;
    display: inline-block;
    position:relative;
    left:0px;
}

.menuicon-me{
    background: url("../assets/me.svg") no-repeat;
    background-size: 30px 30px;
    background-position: center center;
    height: 30px;
    width: 30px;
    display: inline-block;
    position:relative;
    left:0px;
}
.clickback:active{
    height: 70px;
    background-color: rgb(241, 236, 236);
}
</style>

<template>
    <div class="menu">
        <el-row>
            <el-col :span="8" class="clickback" @click=GoGroup><div class="grid-content" />
                <i class = "menuicon-group"></i>
                <p>群组</p>
            </el-col>
            <el-col :span="8" class="clickback" @click=GoContact><div class="grid-content" />
                <i class = "menuicon-contact"></i>
                <p>联系人</p> 
            </el-col>
            <el-col :span="8" class="clickback" @click=GoPerson><div class="grid-content clickback" />
                <i class = "menuicon-me"></i>
                <p>我的</p>
            </el-col>
        </el-row>
    </div>
</template>