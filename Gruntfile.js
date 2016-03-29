module.exports = function(grunt) {

  grunt.initConfig({


    /* css minify Task ********************************************************/
    cssmin: {
      options: {
        shorthandCompacting: false,
        roundingPrecision: -1
      },
      target: {
        files: {
        //  'dist/css/penske-framework.min.css': ['dist/css/penske-framework.css']
        }
      }
    },

    /* Copy Task **************************************************************/
    copy: {
      main: {
        files: [
          {
              // Minified penske-framework css
          //  src: 'dist/css/penske-framework.min.css',
            //dest: 'docs/old-docs-new/dist/css/penske-framework.min.css'
          },
          {
              // common.ja
          //  src: 'dist/js/common.min.js',
            //dest: 'docs/old-docs-new/dist/js/common.min.js'
          },
          {
            //Future js stuff
          }

        ]
      },
    },

    /* Watch Task **************************************************************/
    watch: {
	  configFiles: {
        files: ['Gruntfile.js'],
        tasks: ['cssmin','copy']
      },
	  css: {
		    files: ['scss/**/*.scss'],
		    tasks: ['sass','cssmin','copy']
	  },
    scripts: {
        files: ['js/*.js'],
        tasks: ['requirejs','copy']
      }
	}

  });


  // Load dem plugins
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-watch');
  //Register dem tasks
  grunt.registerTask('default',['watch']);

};
