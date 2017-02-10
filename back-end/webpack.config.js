var path = require('path');
var nodeExternals = require('webpack-node-externals');

module.exports = {
  entry: {
    hello_get: './src/get/handler.js',
  },
  target: 'node',
  externals: [nodeExternals({
    whitelist: ['yelp']
  })],  module: {
    loaders: [{
      test: /\.js$/,
      loaders: ['babel'],
      include: path.resolve(__dirname, 'src'),
    }]
  },
  output: {
    libraryTarget: 'commonjs',
    path: path.join(__dirname, '.webpack'),
    filename: '[name].js'
  },
};
