module.exports = {
  lintOnSave: false,
  outputDir: 'target/dist',
  assetsDir: 'static',
  devServer: {
    proxy: {
      '/api': {
        target: process.env.VUE_APP_BASE_URL,
        ws: true,
        changeOrigin: true
      }
    },
    overlay: {
      warnings: true,
      errors: true
    }
  },
  transpileDependencies: ['vuetify']
};
