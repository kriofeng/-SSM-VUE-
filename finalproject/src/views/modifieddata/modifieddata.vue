<template>
    <div id="show_content">
		<div id="show">
		<div >
			<span>上传图片修改头像</span>
			<input type="file" id="myFile" style="width: 70px;height: 50px" @change="handlerUpload($event)"/>
			<img :src="pic" style="height: 200px; width: 200px"/>
		</div>
		<el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
			<el-form-item label="用户名" prop="age">
			  <el-input v-model.number="ruleForm.name"></el-input>
			</el-form-item>
		  <el-form-item label="密码" prop="pass">
		    <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
		  </el-form-item>
		  <el-form-item label="确认密码" prop="checkPass">
		    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
		  </el-form-item>

		  <el-form-item>
		    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
		    <el-button @click="resetForm('ruleForm')">重置</el-button>
		  </el-form-item>
		</el-form>
		</div>
		<div align="right" style="margin-top: 450px;">
			<el-button type="text" @click="open">退出登录</el-button>
			<el-button type="text" @click="cancle">注销账号</el-button>
		</div>
    </div>
</template>

<script>
  import {ElMessage} from "element-plus";

  export default {
    data() {
      var checkAge = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('用户名不能为空'));
        }
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
      	pic:"",
        ruleForm: {
          pass: '',
          checkPass: '',
          name: ''
        },
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          name: [
            { validator: checkAge, trigger: 'blur' }
          ]
        },
		imageUrl: '',
		  cancleuser:[],
		  updateinfo:[],
      };
    },
    methods: {
		handlerUpload(){
			let param = new FormData();
			param.append("title", "krio");
			var f = document.getElementById("myFile");
			param.append("file", f.files[0]);
			console.log(param.get("file"));
			this.$axios.post("api/img",param).then(rst=>{
				this.pic = rst.data;
			}).catch(ex=>{
				console.log(ex);
			});
			},
      submitForm() {
		  this.updateinfo.userId =sessionStorage.getItem("userId");
		  this.updateinfo.userName =this.ruleForm.name;
		  this.updateinfo.userPwd=this.ruleForm.pass;
		  this.updateinfo.headPortrait = this.pic;
		  if(!/^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$/.test(this.updateinfo.userName)||              //姓名必须为2-20位的字母数字或汉字组合
				  !/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/.test(this.updateinfo.userPwd))//密码为6-12为的字母数字组合
		  {
			  ElMessage.warning({
				  message: '用户名、密码不能为空！！！',
				  type: 'warning'
			  });
		  }else{
			  this.updateinfo.userPwd=this.ruleForm.pass + this.updateinfo.userId;
			  var param = this.$qs.stringify(this.updateinfo);
			  this.$confirm('确定修改个人信息?', '修改信息', {
				  confirmButtonText: '确定',
				  cancelButtonText: '取消',
				  type: 'danger',
			  }).then(() => {
				  console.log(param);
				  this.$axios.post("user/updateuserinfo",param).then(rst => {
					  if(rst.data == 1){
						  ElMessage.success({
							  message: '操作成功！',
							  type: 'success'
						  });
						  this.$router.push("/frontpage");
					  }else{
						  ElMessage.warning({
							  message: '操作失败！',
							  type: 'warning'
						  });
					  }
				  }).catch(ex=>{
					  console.log(ex);
					  ElMessage.error({
						  message: '服务器错误！',
						  type: 'error'
					  });
				  });
			  }).catch(() => {
				  this.$message({
					  type: 'info',
					  message: '已取消操作'
				  });
			  });
		  }



        /*this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });*/
      },
	  open() {
		  this.$confirm('确定退出登录？', '退出登录', {
			  confirmButtonText: '确定',
			  cancelButtonText: '取消',
			  type: 'danger',
		  }).then(() => {
			  this.$router.push("/login");
			  sessionStorage.clear();
		  }).catch(() => {
			  this.$message({
				  type: 'info',
				  message: '已取消操作'
			  });
		  });
	  },
	  cancle() {
		  this.$confirm('确定注销账号?', '注销账号', {
			  confirmButtonText: '确定',
			  cancelButtonText: '取消',
			  type: 'danger',
		  }).then(() => {
			  this.cancleuser.userId =sessionStorage.getItem("userId");
			  var param = this.$qs.stringify(this.cancleuser);
			  console.log(param);
			  this.$axios.post("user/cancleuser",param).then(rst => {
				  if(rst.data == 1){
					  ElMessage.success({
						  message: '操作成功！',
						  type: 'success'
					  });
				  }else{
					  ElMessage.warning({
						  message: '操作失败！',
						  type: 'warning'
					  });
				  }
			  }).catch(ex=>{
				  console.log(ex);
				  ElMessage.error({
					  message: '服务器错误！',
					  type: 'error'
				  });
			  });
		  }).catch(() => {
			  this.$message({
				  type: 'info',
				  message: '已取消操作'
			  });
		  });
	  },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
	        handleAvatarSuccess(res, file) {
	          this.imageUrl = URL.createObjectURL(file.raw);
	        },
	        beforeAvatarUpload(file) {
	          const isJPG = file.type === 'image/jpeg';
	          const isLt2M = file.size / 1024 / 1024 < 2;
	  
	          if (!isJPG) {
	            this.$message.error('上传头像图片只能是 JPG 格式!');
	          }
	          if (!isLt2M) {
	            this.$message.error('上传头像图片大小不能超过 2MB!');
	          }
	          return isJPG && isLt2M;
	        }
    }
  }
</script>

<style scoped>
    #show_content{
        text-align: center;
        background-color: #fff;
        border-radius: 20px;
        /*width: 100%;
        height: 100%;
        margin-top: 30px;*/
        position: absolute;
        margin: auto;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        height: 80%;
        width:  50%;
		margin-top: 10%;
    }
	#show{
	    text-align: center;
	    background-color: #fff;
	    border-radius: 20px;
	    /*width: 100%;
	    height: 100%;
	    margin-top: 30px;*/
	    position: absolute;
	    margin: auto;
	    top: 0;
	    left: 0;
	    right: 0;
	    bottom: 0;
	    width:  60%;
	}
	.avatar-uploader .el-upload {
	  border: 1px dashed #d9d9d9;
	  border-radius: 6px;
	  cursor: pointer;
	  position: relative;
	  overflow: hidden;
	}
	.avatar-uploader .el-upload:hover {
	  border-color: #409EFF;
	}
	.avatar-uploader-icon {
	  font-size: 18px;
	  color: #8c939d;
	  width: 178px;
	  height: 178px;
	  line-height: 178px;
	  text-align: center;
	}
	#ss {
	  width: 178px;
	  height: 178px;
	  display:block;
	}
</style>