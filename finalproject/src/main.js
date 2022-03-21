import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from "axios";
import ElementPlus from 'element-plus'
// import 'element-plus/dist/index.css'
import qs from "qs"
import VueParticles from "vue-particles";
import store from './store'
import installElementPlus from './plugins/element'
import './assets/css/icon.css'




var app = createApp(App)

axios.defaults.baseURL = "http://localhost:8088/"
app.config.globalProperties.$axios = axios
app.config.globalProperties.$qs = qs

installElementPlus(app)
app.use(VueParticles)
app.use(ElementPlus)
app.use(store)
app.use(router).mount('#app')


// router.beforeEach((to,from,next)=>{
//     if(to.path == "/adminLogin"){
//         next();
//     }else{
//         let token = sessionStorage.getItem("token");
//         token ? next():next("/adminLogin");
//     }
// });
