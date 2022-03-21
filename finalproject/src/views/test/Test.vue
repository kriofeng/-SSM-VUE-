<template>
    <div >
        <el-button v-on:click="test">
            ssss
        </el-button>

        <div id="app" class="m-5">
            <div class="uploadBox">
                <h3>上传文件</h3>
                <div class="fileBox">
                    <input type="file" id="myFile"  class="inputfile" @change="handlerUpload($event)"/>
                    <label for="myFile">
                        <i class="iconfont">&#xe632;</i>点击上传本地文件
                    </label>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
    export default {
        data() {
            return {
                posts:[]
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
                    console.log(rst);
                }).catch(ex=>{
                    console.log(ex);
                });
            },
            test(){
                this.posts.title = "t23";
                var param = this.$qs.stringify(this.posts);
                console.log("xx");
                this.$axios.post("api/img",param).then(rst=>{

                }).catch(ex=>{
                    console.log(ex);
                });
            }
        }
    }
</script>

<style scoped>

</style>