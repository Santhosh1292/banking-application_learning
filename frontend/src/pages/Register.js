import { useState } from "react";

function Register() {
  const [name, setName] = useState("");
  const [balance, setBalance] = useState("");

  const handleRegister = async () => {
    const response = await fetch("http://localhost:8080/accounts", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        accountHolderName: name,
        balance: balance
      })
    });

    const data = await response.json();
    console.log(data);
    alert("Account Created!");
  };

  return (
    <div>
      <h2>Create Account</h2>

      <input
        type="text"
        placeholder="Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />

      <br /><br />

      <input
        type="number"
        placeholder="Initial Balance"
        value={balance}
        onChange={(e) => setBalance(e.target.value)}
      />

      <br /><br />

      <button onClick={handleRegister}>Create Account</button>
    </div>
  );
}

export default Register;