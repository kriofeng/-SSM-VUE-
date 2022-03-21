<template>
    <div id="container">

            <div class="resetPassword">
                <div class="container">
                    <el-steps :active="active" :space="200" finish-status="success"  align-center>
                        <el-step title="验证用户Id和邮箱" icon="el-icon-edit"></el-step>
                        <el-step title="输入验证码" icon="el-icon-s-promotion"></el-step>
                        <el-step title="设置新密码" icon="el-icon-key"></el-step>
                    </el-steps>
                    <div v-if="active === 0" class="common_div">
                        <el-form :model="Form"  class="user-container" label-position="left" label-width="60px" size="medium">
                            <el-form-item  style="float: right; width: 80%" label="用户Id">
                                <el-input type="text" v-model="Form.userId" autofocus ref="userId" auto-complete="off"
                                          placeholder="请输入要找回密码的用户Id" prefix-icon="el-icon-user-solid" spellcheck="false">
                                </el-input>
                            </el-form-item>
                            <el-form-item style="float: right; width: 80%" label="邮箱号">
                                <el-input type="text" v-model="Form.email" autofocus ref="email" auto-complete="off"
                                          placeholder="请输入用来找回密码的邮箱" prefix-icon="el-icon-message" spellcheck="false">
                                </el-input>
                            </el-form-item>
                        </el-form>
                    </div>
                    <div v-if="active === 1" class="common_div">
                        <el-form :model="codeForm"  class="user-container" label-position="left" label-width="60px" size="medium">
                            <el-form-item  style="float: right; width: 80%" label="验证码">
                                <el-input type="text" v-model="codeForm.code" autofocus ref="code" auto-complete="off"
                                          placeholder="请输入邮箱验证码" prefix-icon="el-icon-s-promotion" spellcheck="false">
                                </el-input>
                            </el-form-item>
                        </el-form>
                    </div>
                    <div v-if="active === 2" class="common_div">
                        <el-form :model="passwordForm"  class="user-container" label-position="left" label-width="60px" size="medium">
                            <el-form-item  style="float: right; width: 80%" label="新密码">
                                <el-input type="password" v-model="passwordForm.password" autofocus ref="password"
                                          auto-complete="off" placeholder="请输入新密码" prefix-icon="el-icon-key" >
                                </el-input>
                            </el-form-item>
                        </el-form>
                    </div>
                    <div class="common_div">
                        <el-button  @click="next" :disabled=false class="action_button">下一步</el-button>
                    </div>

                </div>
            </div>
    </div>
</template>

<script>
    export default {
        name: "RetrievePassword",
        data() {
            return {
                active: 0,
                Form: {
                    userId: '',
                    email: '',
                },
                codeForm: {
                    code: '',
                },
                passwordForm: {
                    password: '',
                },
                disabled: false,
                isUsername:false,
                posts:[]
            }
        },
        created() {
        },
        methods: {
            isEmail() {
                let regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
                if (!regEmail.test(this.Form.email)) {
                    this.$message({
                        message: '邮箱格式不正确',
                        type: 'error'
                    });
                    return false;
                }
                return true;
            },
            beforePost () {
                if (this.Form.userId === '' || this.Form.email === '') {
                    // 重置密码时，如果用户名或者邮箱未输入，那么提醒用户输入
                    if (this.Form.userId === '') {
                        this.$message({
                            message: '请输入用户Id',
                            type: 'warning'
                        });
                        this.$refs.userId.focus();
                    }else {
                        this.$message({
                            message: '请输入邮箱',
                            type: 'warning'
                        });
                        this.$refs.email.focus();
                    }
                    return false;
                }else {
                    //进行邮箱格式的检测
                    return this.isEmail();
                }
            },
            next() {
                // 当面板为0时，先判断用户名和邮箱是否输入，进行相关的验证
                if (this.active === 0) {
                    let isFinished = this.beforePost();
                    if (isFinished) {
                        //数据输入正确后，将按钮禁掉，并提示相关信息，然后数据发送到后台
                        this.disabled = true;
                        this.$notify.info({
                            title: '提示',
                            message: '数据正确发送，请耐心等待，勿重复操作！',
                            duration: 0,
                            offset: 100,
                        });
                        this.post();
                    }
                }
                // 当面板为1时，则到了用户输入验证码的时候, 将验证码传入后台
                if (this.active === 1) {
                    // 如果验证码未输入，提示用户
                    if (this.codeForm.code === '') {
                        this.$notify({
                            title: '警告',
                            message: '警告, 验证码未输入，请去您邮箱中查看!',
                            type: 'warning',
                            offset: 100,
                        });
                        this.$refs.code.focus();
                    }else {
                        this.posts=[];
                        this.posts.userId = this.Form.userId;
                        this.posts.code = this.codeForm.code;
                        var param = this.$qs.stringify(this.posts);
                        console.log(param);
                        this.$axios.post('/reset/resetPwd',param).then(successResponse => {
                            if (successResponse.data.code === 200) {
                                //验证码输入正确，
                                this.active++;
                                this.$notify({
                                    title: '成功',
                                    message: '验证码匹配正确！',
                                    type: 'success',
                                    duration: 0,
                                    offset: 100,
                                });
                            }else if (successResponse.data.code === 400) {
                                //验证码匹配错误返回对应信息
                                this.$message.error(successResponse.data.message);
                            }
                        }).catch(failResponse => {

                        })
                    }

                }
                // 当面板为2时，则到了用户输入密码的时候, 将密码传入后台
                if (this.active === 2) {
                    //在发送密码之前，先校验一下是否输入了，不能让用户不小心输入了空密码
                    if (this.passwordForm.password === '') {
                        this.$notify({
                            title: '警告',
                            message: '警告, 新密码未输入',
                            type: 'warning',
                            offset: 100,
                        });
                        this.$refs.password.focus();
                    }else {
                        this.posts=[];
                        this.posts.userId = this.Form.userId;
                        this.posts.userPwd = this.passwordForm.password + this.Form.userId;
                        var param = this.$qs.stringify(this.posts);
                        console.log(param)
                        this.$axios.post('/reset/resetPwd', param).then(successResponse => {
                            if (successResponse.data.code === 200) {
                                //密码修改成功
                                this.$notify({
                                    title: '成功',
                                    message: '该账号密码修改正确！',
                                    type: 'success',
                                    duration: 0,
                                    offset: 100,
                                });
                                let path = this.$route.query.redirect;
                                this.$router.replace({path: path === '/' || path === undefined ? '/login' : path})
                            }else if (successResponse.data.code === 400) {
                                //修改密码失败，返回对应信息
                                this.$message.error(successResponse.data.message);
                            }
                        }).catch(failResponse => {

                        })
                    }
                }
            },
            post(){
                console.log(this.Form.userId)
                console.log(this.Form.email)
                this.posts.userId = this.Form.userId;
                this.posts.userEmail = this.Form.email;
                var param = this.$qs.stringify(this.posts);
                this.$axios
                    .post('/reset/resetPwd', param)
                    .then(successResponse => {
                        if (successResponse.data.code === 200) {
                            // 如果返回的结果正确，那么需要发送邮件到对应的用户邮箱中，用户自己登录邮箱后找到对应的链接后才可以输入新密码
                            this.$notify({
                                title: '成功',
                                message: '已向'+ this.Form.email + '发送验证码，请在5分钟之内修改密码，否则验证码失效',
                                type: 'success',
                                duration: 0,
                                offset: 100
                            });
                            //跳转到下一个面板，并且将按钮恢复正常
                            this.active++;
                            this.disabled = false;
                            //将用户userId保存下来
                            this.userId = successResponse.data.result;
                        }else if (successResponse.data.code === 400) {
                            //如果用户名和密码匹配错误，那么显示错误信息，并让按钮重新可用
                            this.$notify({
                                title: '失败',
                                message: successResponse.data.message + "未知错误！",
                                type: 'error',
                                duration: 0,
                                offset: 100,
                            });
                            this.disabled = false;
                        }
                    })
                    .catch(failResponse => {
                    })
            },
        }
    }
</script>


<style scoped>

    .resetPassword{
        background-image: url("../../assets/retrieveBackground.jpg");
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
    }
    .container{
        border-radius: 15px;
        background-clip: padding-box;
        margin: 10% auto;
        width: 30%;
        padding: 25px 30px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
        opacity: 0.7;
    }
    .common_div{
        margin-top: 5%;
    }
    .user-container {
        width: 80%;
        background: #fff;

    }
    .action_button {
        width: 20%;
        margin-top: 3%;
        text-align: center;
    }

</style>