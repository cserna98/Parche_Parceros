import React, { createContext, useContext, useState } from 'react';

// Crea un nuevo contexto
const AuthContext = createContext();

// Crea un componente proveedor que envuelve tu aplicación
export const AuthProvider = ({ children }) => {
  const [token, setToken] = useState(null);

  // Función para establecer el token
  const setAuthToken = (newToken) => {
    setToken(newToken);
  };

  return (
    <AuthContext.Provider value={{ token, setAuthToken }}>
      {children}
    </AuthContext.Provider>
  );
};

// Hook personalizado para acceder al contexto
export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth debe ser utilizado dentro de un AuthProvider');
  }
  return context;
};
