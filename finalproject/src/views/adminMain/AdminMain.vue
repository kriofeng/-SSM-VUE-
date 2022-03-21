<template>
    <div id="container">
        <h1>5555555</h1>
        <button v-on:click="jump()">帖子管理</button>
    </div>
</template>

<script>
    export default {
        name: "AdminMain",
        data(){
            return{

            }
        },
        methods:{
            jump(){
                this.$router.push("../adminPostControl")
            }
        }
    }
</script>

<style scoped>

</style>