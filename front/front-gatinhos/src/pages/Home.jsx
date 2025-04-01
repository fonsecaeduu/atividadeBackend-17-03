import { useEffect, useState } from 'react';

export default function Home() {
  const [catData, setCatData] = useState(null);
  const [error, setError] = useState(null);

  const fetchCat = async () => {
    try {
      const response = await fetch('http://localhost:8080/gatinhos');
      
      if (!response.ok) {
        throw new Error(`Erro HTTP: ${response.status}`);
      }

      const data = await response.json();
      setCatData(data);
    } catch (err) {
      setError(err.message);
      console.error("Erro detalhado:", err);
    }
  };

  useEffect(() => { fetchCat(); }, []);

  return (
    <div>
      {error ? (
        <div>
          <p>Erro: {error}</p>
          <button onClick={fetchCat}>Tentar novamente</button>
        </div>
      ) : catData ? (
        <div>
          <img 
            src={catData[0].url} 
            alt="Gatinho" 
            style={{ maxWidth: '500px' }}
          />
          <button onClick={fetchCat}>Carregar outro</button>
        </div>
      ) : (
        <p>Carregando...</p>
      )}
    </div>
  );
}