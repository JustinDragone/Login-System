var login = require('config/login');

module.exports = function(app) {

	app.get('/', function(req, res) {

		res.end("Login System"); 
	});

	app.post('/login',function(req,res){
		var username = req.body.username;
        	var password = req.body.password;

		login.login(username,password,function (found) {
			console.log(found);
			res.json(found);
	});
	});

};



