<template>
    <div id="container">
        <div id="login_div" >
            <el-form ref="login_form"
                     label-width="80px">
                <el-form-item label="用户ID">
                    <el-input v-model="user.userId" placeholder="请输入内容" style="width: 200px" clearable>
                        <template #prepend>
                            <i class="el-icon-user"></i>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item label="用户名">
                    <el-input v-model="user.userName" placeholder="请输入内容" style="width: 200px" clearable>
                        <template #prepend>
                            <i class="el-icon-user-solid"></i>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio v-model="user.userSex" label="1">男</el-radio>
                    <el-radio v-model="user.userSex" label="2">女</el-radio>
                </el-form-item>
                <el-form-item label="用户邮箱">
                    <el-input v-model="user.userEmail" placeholder="请输入内容" style="width: 200px" clearable>
                        <template #prepend>
                            <i class="el-icon-user-solid"></i>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="user.userPwd" placeholder="请输入内容" style="width: 200px" clearable>
                        <template #prepend>
                            <i class="el-icon-user-solid"></i>
                        </template>
                    </el-input>
                </el-form-item>
                <el-row>
                    <el-button type="primary" v-on:click="reg">注册</el-button>
                    <el-button type="primary" v-on:click="reback">返回</el-button>
                </el-row>
            </el-form>
        </div>
    </div>
</template>

<script>
    import { ElMessage } from 'element-plus'
    export default {
        name: "Login_Reg",
        data:function () {

            return{
                user:{
                    userId:'',
                    userName:'',
                    userSex:'',
                    userEmail:'',
                    userPwd:''
                }

            }

        },

        methods:{
            reg(){
                if(!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{4,12}$/.test(this.user.userId) ||    //正则表达式判断注册信息 用户Id必须为4-12的字母加数字组合
                    !/^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$/.test(this.user.userName)||              //姓名必须为2-20位的字母数字或汉字组合
                    this.user.userSex ==''||                                                    //性别不为空
                    !/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(this.user.userEmail)||//邮箱必须符合格式
                    !/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/.test(this.user.userPwd))    //密码为6-12为的字母数字组合
                {
                    ElMessage.warning({
                        message: '请正确填写所有信息！！！',
                        type: 'warning'
                    });
                }else{
                    this.user.userPwd = this.user.userPwd+this.user.userId;
                    var param=this.$qs.stringify(this.user);
                    console.log(param)
                    this.$axios.post("http://localhost:8088/user/reg",param)
                        .then(rst=>{
                            console.log(rst);
                            if(rst.data == 1){
                                ElMessage.success({
                                    message: '恭喜你，注册成功~',
                                    type: 'success'
                                });
                                this.reback();
                            }else{
                                ElMessage.warning({
                                    message: '输入信息有误！',
                                    type: 'warning'
                                });
                            }
                        }).catch(ex=>{
                        console.log(ex);

                    });
                }
            },
            reback(){
                this.$router.push("/login")
            },

        }
    }
</script>

<style scoped>

    #container{
        width: 100%;
        height: 100%;
        background-color: #9fcdff;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    #login_div{
        border-radius: 15px;
        background-clip: padding-box;
        margin: 90px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #dfedff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
</style>