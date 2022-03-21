<template>
    <div id="headers">
        <el-menu
                :default-active="activeIndex"
                class="el-menu-demo"
                mode="horizontal"
                @select="handleSelect"
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#ffd04b">
            <el-menu-item v-on:click="mai" index="1" style="margin-top: 10px; width: 100px; margin-left: 20px;">首页</el-menu-item>
            <el-menu-item v-on:click="blc" index="2" style="margin-top: 10px;width: 100px;">博客</el-menu-item>
            <el-menu-item v-on:click="fm" index="3" style="margin-top: 10px;width: 100px;" >论坛</el-menu-item>
            <div style="margin-left: 1100px;">
                <table style="float: left;">
                    <tr>
                        <td>
                            <el-link v-on:click="nct" style="color: white;">&#8194;&#8194;消息中心&#8194;</el-link>
                        </td>
                        <td>
                            <el-link v-on:click="edi" style="color: white;">&#8194;&#8194;博客创作&#8194;</el-link>
                        </td>
                        <td>
                            <el-link v-on:click="fop" style="color: white;">&#8194;&#8194;论坛创作&#8194;&#8194;</el-link>
                        </td>
                        <td>
                            <div style="margin-top: 20px; margin-left: 5px;">
                                <el-avatar :size="35" :src="userInfo.headPortrait"><!--头像--></el-avatar>
                            </div>

                        </td>
                    </tr>
                </table>
            </div>
        </el-menu>
    </div>
    <div id="children">
    <router-view></router-view>
    </div>
</template>

<script>
    export default {
        name: 'index',
        data(){
            return {
                userInfo:{
                    userId:'',
                    headPortrait:''
                },
                posts:[],
            };
        },
        created() {
            this.init();
        },
        methods: {
            init(){
                this.posts.userId = sessionStorage.getItem("userId");
                var param = this.$qs.stringify(this.posts);
                this.$axios.post("user/findUser",param).then(rst=>{
                    console.log(rst);
                    if(rst.data != null){
                        this.userInfo.userId = rst.data.userId;
                        this.userInfo.headPortrait = rst.data.headPortrait;
                    }
                }).catch(ex=>{
                    console.log(ex);
                });
            },
            mai(){
                this.$router.push("/frontpage")
            },
            edi(){
                this.$router.push("/Edit")
            },
            uct(){
                this.$router.push("/userct")
            },
            fop(){
                this.$router.push("/Publish")
            },
            fm(){
                this.$router.push("/forumMain")
            },
            nct(){
                this.$router.push("/notict")
            },
            blc(){
                this.$router.push("/BlogMain")
            }
        }
    }
</script>

<style scoped>

    #headers{
        width: 100%;
        text-align: center;
        line-height: 50px;
    }



</style>