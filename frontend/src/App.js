import { useState } from "react";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";

function App() {
  const [page, setPage] = useState("login");

  return (
    <div>
      <button onClick={() => setPage("login")}>Login</button>
      <button onClick={() => setPage("register")}>Register</button>
      <button onClick={() => setPage("dashboard")}>Dashboard</button>

      {page === "login" && <Login />}
      {page === "register" && <Register />}
      {page === "dashboard" && <Dashboard />}
    </div>
  );
}

export default App;