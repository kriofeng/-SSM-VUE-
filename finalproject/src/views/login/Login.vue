<template>
    <div class="login-info">
        <vue-particles
                color="#fff"
                :particleOpacity="0.7"
                :particlesNumber="60"
                shapeType="circle"
                :particleSize="4"
                linesColor="#fff"
                :linesWidth="1"
                :lineLinked="true"
                :lineOpacity="0.4"
                :linesDistance="150"
                :moveSpeed="2"
                :hoverEffect="true"
                hoverMode="grab"
                :clickEffect="true"
                clickMode="push"
                class="lizi"
                style="height:100%"
        ></vue-particles>
        <div id="input">
<!--        <el-row justify="center" align="middle">-->
<!--            <el-col :xs="24" :sm="11" :md="8" :lg="9" :xl="8">-->
            <div class="userloginpart">
                <div id="text_login">BFM</div>
				<div id="text_log">有问题 就会有答案</div>
                <el-form :model="ruleForm"  :rules="rules" ref="ruleForm"  class="demo-ruleForm">
                    <el-form-item  prop="account">
                        <el-input placeholder="请输入账户名或者账号ID" v-model="userId" clearable>
                        <template slot="prepend">账号：</template>
                    </el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input type="password" placeholder="请输入密码" v-model="userPwd" show-password clearable>
                            <template slot="prepend">密码：</template>
                        </el-input>
                    </el-form-item>
                    <el-form-item >
                        <el-row>
                            <el-col :span="14" class="vaildCode"
                            ><el-input
                                    placeholder="验证码"
                                    v-model="code"
                                    clearable
                                    prop="security"
                            >
                            </el-input>
                            </el-col>
                            <el-col :span="10" class="vaildCode">
                                <div class="code" @click="refreshCode">
                                    <security :identifyCode="identifyCode"></security>
                                </div>
                            </el-col>
                        </el-row>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" icon="el-icon-upload" class="lgbtn"
                                   @click="toHome" >登录</el-button>

                    </el-form-item>
                    <div class="tips"  style="float:left;">
                        <el-link type="white" @click="retrievePWD">忘记密码</el-link>
                    </div>
                    <div class="tips"  style="float:right;">
                        <el-link type="white" @click="reg">还没有账号？点击注册</el-link>              </div>
                </el-form>
            </div>
<!--            </el-col>-->
<!--        </el-row>-->
        </div>
    </div>

</template>

<script>
    import security from "../../components/security"
    export default {
        name: 'Login',
        components: {
            security
        },
        data(){
            return{
                userId: '',
                userPwd: '',

                code: '',
                // 随机抽取四位数
                identifyCodes: '1234567890abcdef',
                identifyCode: '',
                checked: false

            }
        },
        mounted() {
            this.identifyCode = ''
            this.makeCode(this.identifyCodes, 4)

        },
        methods:{
            randomNum(min, max) {
                return Math.floor(Math.random() * (max - min) + min)
            },
            // 刷新验证码
            refreshCode() {
                this.identifyCode = ''
                this.makeCode(this.identifyCodes, 4)
            },
            makeCode(o, l) {
                for (let i = 0; i < l; i++) {
                    this.identifyCode += this.identifyCodes[
                        this.randomNum(0, this.identifyCodes.length)
                        ]
                }
                console.log(this.identifyCode)
            },
            //点击登录按钮执行访问请求并判断是否登陆
            toHome(){
                if(this.code != this.identifyCode){
                    this.$notify({
                        title: '提示',
                        message: '验证码错误！',
                        duration: 3000
                    });
                    this.refreshCode();
                }else{
                this.$axios({
                    method: 'post',
                    url: 'http://localhost:8088/user/login',
                    data: this.$qs.stringify({          //axios的post方法访问后端this.qs.stringify(
                        userId: this.userId,
                        userPwd: this.userPwd + this.userId
                    }),
                })
                    .then(res=>{                    //请求成功后执行函数
                        if(res.data.userId ){
                            sessionStorage.setItem("userId",res.data.userId);
                            this.$router.push('/frontpage')	//登录验证成功路由实现跳转
                            this.$notify({
                                title: '提示',
                                message: '用户登录成功',
                                duration: 3000
                            });
                        }else{
                            this.$notify({
                                title: '提示',
                                message: '用户名或密码错误',
                                duration: 3000
                            });
                            this.refreshCode();
                        }
                    })
                    .catch(err=>{                   //请求错误后执行函数
                        his.$notify({
                            title: '提示',
                            message: '用户访问错误',
                            duration: 3000
                        });
                        console.log(err)
                    })
            }
            },
            reg(){
                this.$router.push("/login_reg")
            },
            retrievePWD(){
                this.$router.push("/retrievepassword")
            }

        },
    }
</script>
<style scoped>

    .userloginpart{
        position:absolute;
        /*定位方式绝对定位absolute*/
        top:50%;
        left:50%;
        /*顶和高同时设置50%实现的是同时水平垂直居中效果*/
        transform:translate(-50%,-50%);
        /*实现块元素百分比下居中*/
        width:450px;
        padding:50px;
        background:rgba(125,181,142,0.5);
        /*背景颜色为黑色，透明度为0.8*/
        box-sizing:border-box;
        /*box-sizing设置盒子模型的解析模式为怪异盒模型，
        将border和padding划归到width范围内*/
        box-shadow: 0px 15px 25px rgba(0,0,0,.5);
        /*边框阴影  水平阴影0 垂直阴影15px 模糊25px 颜色黑色透明度0.5*/
        border-radius:15px;
        /*边框圆角，四个角均为15px*/
    }
    #text_login{
        color: #fff;
        text-align:center;
		font-size: 25px;
        /*文字居中*/		
		font-weight:bold;
    }
	#text_log{
	    padding:0;
	    color: #fff;
	    text-align:center;
		margin-bottom: 20px;
		font-size: 17px;

		}
    .userloginPart .inputElement input{
        width: 100%;
        font-size:16px;
        color:#fff;
        letter-spacing: 1px;
        /*字符间的间距1px*/
        margin-bottom: 30px;
        border:none;
        border-bottom: 1px solid #fff;
        outline:none;
        /*outline用于绘制元素周围的线
        outline：none在这里用途是将输入框的边框的线条使其消失*/
        background: transparent;
        /*背景颜色为透明*/
    }

    .lgbtn{
        display: block;
        margin-left: auto;
        margin-right: auto;
        height: 25px;
        width: 250px;
    }
    #particles-js {
        width: 100%;
        height: 100%;
        position: absolute;
    }

    .login-info {
        background-image: linear-gradient(-180deg, #33ffe6 0%, rgba(89, 165, 117, 0.8) 100%);
        /*background-image: url("../images/bg_login.png");*/
        background-repeat: no-repeat;
        background-size: cover;
        height: 100%;
        width: 100%;
    }
    .login-info  .el-col{
        background-color: #a3ecc5;
        padding: 2% 5% 0% 2%;


    }

    .el-row {
        height: 100%;
    }
    .el-col .login-info{
        height: 50px;
        background-color: #70c29d;
    }

    .login-info .el-col[data-v-2529d779] {
        background-color: #70c29e;
        padding: 2% 5% 0% 2%;
    }

    /*.demo-ruleForm{*/
    /*    border-radius: 15px;*/
    /*    background-clip: padding-box;*/
    /*    width: 350px;*/
    /*    padding: 15px 35px 15px 35px;*/
    /*}*/



</style>


