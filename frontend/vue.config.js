module.exports = {
  outputDir: 'target/dist',
  assetsDir: 'static',
  overlay: {
    warnings: true,
    errors: true
  },
  devServer: {
    proxy: {
      '/api': {
        ws: true,
        changeOrigin: true
      }
    }
  },
  transpileDependencies: ['vuetify']
};
