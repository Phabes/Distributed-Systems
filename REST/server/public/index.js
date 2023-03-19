let controller = new AbortController();
// const url = "http://localhost:5000/temperature";
const url = "https://weather-app-5y8o.onrender.com/temperature";
const errorDv = document.getElementById("error");
const websitesDv = document.getElementById("websites");
const averagesDv = document.getElementById("averages");
const extremesDv = document.getElementById("extremes");

document.getElementById("form").addEventListener("submit", (e) => {
  e.preventDefault(); //comment
  clearData();
  controller.abort();
  controller = new AbortController();
  const signal = controller.signal;

  const formData = new FormData(e.target);
  const formProps = Object.fromEntries(formData);

  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(formProps),
    signal: signal,
  };
  fetch(url, requestOptions)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      const { action } = data;
      if (action == "Data Retrieved") {
        const { websites, averages, extremes } = data;
        websites.forEach((element) => {
          const dv = document.createElement("div");
          dv.className = "dataRow";
          dv.innerHTML = element;
          websitesDv.appendChild(dv);
        });
        averages.forEach((element) => {
          const dv = document.createElement("div");
          dv.className = "dataRow";
          dv.innerHTML = element + "&deg;";
          averagesDv.appendChild(dv);
        });
        extremes.forEach((element) => {
          const dv = document.createElement("div");
          dv.className = "dataRow";
          dv.innerHTML =
            "MAX: " + element.max + "&deg, MIN: " + element.min + "&deg;";
          extremesDv.appendChild(dv);
        });
      } else errorDv.innerHTML = action;
    })
    .catch((error) => console.log(error));
});

const clearData = () => {
  errorDv.innerHTML = "";
  websitesDv.innerHTML = "";
  averagesDv.innerHTML = "";
  extremesDv.innerHTML = "";
};
