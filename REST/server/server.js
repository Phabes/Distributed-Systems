const express = require("express");
const cors = require("cors");
const fetch = require("node-fetch");
const auth = require("./middleware/auth");
const retrieve = require("./data/status");
const app = express();
const PORT = process.env.PORT || 5000;

app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(express.static("public"));

app.get("/weather", function (req, res) {
  res.sendFile(__dirname + "/public/index.html");
});

app.post("/temperature", auth, async (req, res) => {
  const { lon, lat, freq } = req.body;
  const goodParameters = checkParameters(lon, lat, freq);
  if (!goodParameters) {
    res.status(400).json({ action: retrieve.bad }); //commment
    // res.status(400).end(`
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
    //         ${retrieve.bad}
    //       </div>
    //     </div>
    //   </body>
    // </html>`);
    return;
  }
  const frequency = freq == "daily" ? "D" : "H";
  const date = new Date();
  const start = date.toISOString().slice(0, 10);
  const end = new Date(date.getTime() + 6 * 24 * 60 * 60 * 1000)
    .toISOString()
    .slice(0, 10);
  const openMeteo =
    freq == "daily"
      ? `https://api.open-meteo.com/v1/forecast?longitude=${lon}&latitude=${lat}&timezone=UTC&daily=temperature_2m_max`
      : `https://api.open-meteo.com/v1/forecast?longitude=${lon}&latitude=${lat}&timezone=UTC&hourly=temperature_2m`;
  await Promise.all([
    fetch(openMeteo),
    fetch(
      `https://www.meteosource.com/api/v1/free/point?lon=${lon}&lat=${lat}&sections=${freq}&timezone=UTC&language=en&units=metric&key=my9z0bvqhf9yewzq24c8mgscw9moidcazqn60agx`
    ),
    fetch(
      `https://api.oikolab.com/weather?param=temperature&start=${start}&end=${end}&lon=${lon}&lat=${lat}&freq=${frequency}&api-key=069c63dc619e45c29034e1099f360250`
    ),
  ])
    .then((responses) => {
      return Promise.all(responses.map((response) => response.json()));
    })
    .then((responses) => {
      const [meteo, meteosource, oikolab] = responses;
      const openMeteoTemperatures =
        freq == "daily"
          ? meteo.daily.temperature_2m_max
          : meteo.hourly.temperature_2m;
      const meteosourceTemperatures =
        freq == "daily"
          ? meteosourceDailyProcessing(meteosource.daily.data)
          : meteosourceHourlyProcessing(meteosource.hourly.data);
      const oikolabTemperatures = oikolabDailyProcessing(
        JSON.parse(oikolab.data).data
      );
      const temperatures = [
        openMeteoTemperatures,
        meteosourceTemperatures,
        oikolabTemperatures,
      ];
      const averages = temperatures.map((temperature) => {
        return getAverageTemperature(temperature);
      });
      const extremes = temperatures.map((temperature) => {
        return getExtremes(temperature);
      });
      res.status(200).json({
        action: retrieve.success,
        websites: ["meteo", "meteosource", "oikolab"],
        averages: averages,
        extremes: extremes,
      }); //commment
      // res.status(200).end(`
      //   <html lang="en">
      //     <head>
      //       <meta charset="UTF-8">
      //       <meta http-equiv="X-UA-Compatible" content="IE=edge">
      //       <meta name="viewport" content="width=device-width, initial-scale=1.0">
      //       <title>Weather API</title>
      //       <link rel="stylesheet" href="index.css">
      //       <script src="index.js" defer=""></script>
      //     </head>
      //     <body>
      //       <div id="data">
      //         <div id="retrieved">
      //           <div class="singleData">
      //             Website
      //             <div id="websites"><div class="dataRow">meteo</div><div class="dataRow">meteosource</div><div class="dataRow">oikolab</div></div>
      //           </div>
      //           <div class="singleData">
      //             Averages
      //             <div id="averages"><div class="dataRow">${averages[0]}&deg;</div><div class="dataRow">${averages[1]}&deg;</div><div class="dataRow">${averages[2]}&deg;</div></div>
      //           </div>
      //           <div class="singleData">
      //             Extremes
      //             <div id="extremes"><div class="dataRow">MAX: ${extremes[0].max}&deg;, MIN: ${extremes[0].min}&deg;</div><div class="dataRow">MAX: ${extremes[1].max}&deg;, MIN: ${extremes[1].min}&deg;</div><div class="dataRow">MAX: ${extremes[2].max}&deg;, MIN: ${extremes[2].min}&deg;</div></div>
      //           </div>
      //         </div>
      //       </div>
      //     </body>
      //   </html>`);
    })
    .catch((error) => {
      res.status(424).json({ action: retrieve.fail }); //commment
      // res.status(424).end(`
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
      //         ${retrieve.fail}
      //       </div>
      //     </div>
      //   </body>
      // </html>`);
    });
});

const checkParameters = (lon, lat, freq) => {
  if (isNaN(lon) || lon < -180 || lon > 180) return false;
  if (isNaN(lat) || lat < -90 || lat > 90) return false;
  if (freq != "daily" && freq != "hourly") return false;
  return true;
};

const meteosourceDailyProcessing = (data) => {
  return data.map((e) => {
    return e.all_day.temperature;
  });
};

const meteosourceHourlyProcessing = (data) => {
  return data.map((e) => {
    return e.temperature;
  });
};

const oikolabDailyProcessing = (data) => {
  return data.map((e) => {
    return e[4];
  });
};

const getAverageTemperature = (temperatures) => {
  return (
    Math.round(
      (temperatures.reduce((a, b) => a + b, 0) / temperatures.length) * 100
    ) / 100
  );
};

const getExtremes = (data) => {
  return {
    max: Math.max(...data).toFixed(2),
    min: Math.min(...data).toFixed(2),
  };
};

app.listen(PORT, () => {
  console.log(`SERVER STARTED ON PORT ${PORT}`);
});
