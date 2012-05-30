modules = {

    scaffolding {
        dependsOn 'bootstrap'
        resource url: 'css/scaffolding.css'
    }

    custom {
        dependsOn 'jquery'
        resource url: '/js/custom.js'
        resource url: '/css/custom.css'
    }

    datepicker {
        dependsOn 'bootstrap'
        resource url:'/js/bootstrap-datepicker.js'
        resource url:'/css/bootstrap-datepicker.css'
    }
}