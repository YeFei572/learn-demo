import Vue from "vue"
import VueRouter from "vue-router"


if (process.env.NODE_ENV === "development") {
    Vue.use(VueRouter);
}

const err401 = () => import("../views/error/err401.vue");
const err404 = () => import("../views/error/err404.vue");


export default new VueRouter({
    mode: "history",
    base: process.env.VUE_APP_ROUTER_BASE,
    routes: [
        {
            path: "*",
            component: err404
        },
        {
            path: "/404",
            component: err404,
            name: "404"
        },
        {
            path: "/401",
            component: err401,
            name: "401"
        },
        {
            path: "/500",
            component: err404,
            name: "500"
        },
        {
            path: "/index",
            component: Home,
            name: "home"
        },
    ]
})