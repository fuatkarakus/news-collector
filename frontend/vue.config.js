module.exports = {
    // Change build paths to make them Maven compatible
    // see https://cli.vuejs.org/config/
    outputDir: 'target/dist',
    assetsDir: 'static',
    overlay: {
        warnings: true,
        errors: true
    },
    devServer: {
        proxy: {
            '/api': {
                target: process.env.VUE_APP_BASE_URL,
                ws: true,
                changeOrigin: true
            }
        }
    }
}
