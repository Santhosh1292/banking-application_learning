import { useState } from "react";

function Dashboard() {
  const [accountId, setAccountId] = useState("");
  const [amount, setAmount] = useState("");
  const [toAccountId, setToAccountId] = useState("");

  const deposit = async () => {
    await fetch(`http://localhost:8080/accounts/${accountId}/deposit`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ amount })
    });
    alert("Deposit Successful");
  };

  const withdraw = async () => {
    await fetch(`http://localhost:8080/accounts/${accountId}/withdraw`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ amount })
    });
    alert("Withdraw Successful");
  };

  const transfer = async () => {
    await fetch("http://localhost:8080/accounts/transfer", {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        fromAccountId: accountId,
        toAccountId: toAccountId,
        amount: amount
      })
    });
    alert("Transfer Successful");
  };

  return (
    <div>
      <h2>Dashboard</h2>

      <input
        type="number"
        placeholder="Account ID"
        value={accountId}
        onChange={(e) => setAccountId(e.target.value)}
      />

      <br /><br />

      <input
        type="number"
        placeholder="Amount"
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
      />

      <br /><br />

      <button onClick={deposit}>Deposit</button>
      <button onClick={withdraw}>Withdraw</button>

      <br /><br />

      <input
        type="number"
        placeholder="To Account ID"
        value={toAccountId}
        onChange={(e) => setToAccountId(e.target.value)}
      />

      <br /><br />

      <button onClick={transfer}>Transfer</button>
    </div>
  );
}

export default Dashboard;