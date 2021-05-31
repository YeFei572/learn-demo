module.exports = {
    baseUrl: process.NODE_ENV === "production" ? process.env.baseUrl:"/",
    outputDir: process.env.outputDir,
    configureWebpack: config => {
        if (process.env.NODE_ENV === "production") {
            // 为生产环境修改配置
            config.externals = {
                vue: "Vue",
                vuex: "Vuex",
                "vue-router": "VueRouter"
            };
        } else {
            // 未开发环境修改配置
        }
    },
    chainWebpack: config => {
        config.plugin("html").tap(args => {
            args[0].template = process.env.template;
            return args;
        })
    },
    devServer: {
        disableHostCheck: true
    }
}