module.exports = {
  devServer: {
    proxy: {
      "/user/*": {
        target: "http://localhost:6660"
      }
    }
  }
};
