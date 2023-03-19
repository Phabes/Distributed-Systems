const retrieve = require("../data/status");

const verify = (req, res, next) => {
  const { login, password } = req.body;
  if (login == "admin" && password == "admin") return next();
  return res.status(401).json({ action: retrieve.unauthorized }); //commment
  // return res.status(401).end(`
  // <html lang="en">
  //   <head>
  //     <meta charset="UTF-8">
  //     <meta http-equiv="X-UA-Compatible" content="IE=edge">
  //     <meta name="viewport" content="width=device-width, initial-scale=1.0">
  //     <title>Weather API</title>
  //     <link rel="stylesheet" href="index.css">
  //   </head>
  //   <body>
  //     <div id="data">
  //       <div id="error">
  //         ${retrieve.unauthorized}
  //       </div>
  //     </div>
  //   </body>
  // </html>`);
};

module.exports = verify;
