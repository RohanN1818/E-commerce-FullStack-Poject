import axios from "axios";

const API = axios.create({
  baseURL: "https://e-commerce-backend-project-3lzz.onrender.com/api",
});
delete API.defaults.headers.common["Authorization"];
export default API;
