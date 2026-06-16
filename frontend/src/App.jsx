import { useEffect, useState } from "react";
import { Client } from "@stomp/stompjs";

const API = "http://localhost:8080/api/tickets";

export default function App() {
  const [ticket, setTicket] = useState(null);
  const [calledTicket, setCalled] = useState(null);
  const [status, setStatus] = useState("Conectando...");

  useEffect(() => {
    const client = new Client({
      brokerURL: "ws://localhost:8080/ws/websocket",
      onConnect: () => {
        setStatus("Conectado");
        client.subscribe("/topic/tickets", (msg) => {
          setCalled(JSON.parse(msg.body));
        });
      },
      onDisconnect: () => setStatus("Desconectado"),
      onStompError: () => setStatus("Error STOMP"),
    });
    client.activate();
    return () => client.deactivate();
  }, []);

  const createTicket = async () => {
    const res = await fetch(API, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ service: "General" }),
    });
    setTicket(await res.json());
  };

  const callNext = () =>
    fetch(`${API}/next`, { method: "POST" });

  return (
    <div>
      <h1>Sistema de Turnos</h1>
      <p>WebSocket: {status}</p>

      <hr />
      <h2>Turno llamado</h2>
      {calledTicket
        ? <p><strong>{calledTicket.id}</strong> — {calledTicket.service}</p>
        : <p>Ninguno aún</p>}

      <hr />
      <h2>Paciente</h2>
      <button onClick={createTicket}>Tomar turno</button>
      {ticket && <p>Tu turno: {ticket.id} ({ticket.status})</p>}

      <hr />
      <h2>Recepción</h2>
      <button onClick={callNext}>Llamar siguiente</button>
    </div>
  );
}
