// Node modules
import { createContext, ReactNode, useContext, useState } from "react";

// Interfaces
interface iProps {
  children: ReactNode;
}
interface iValues {
  modal: ReactNode | null;
  setModal: Function;
}

// Properties
const initialValues: iValues = {
  modal: null,
  setModal: () => {},
};
const Context = createContext(initialValues);

// Methods
export function ModalProvider({ children }: iProps) {
  // Local state
  const [modal, setModal] = useState(null);

  // Properties
  const value: iValues = { modal, setModal };

  return <Context.Provider value={value}>{children}</Context.Provider>;
}

export function useModal() {
  const context = useContext(Context);
  const errorText =
    "To use useModal(), you need to wrap the parent component with <ModalProvider/>";

  // Safeguards
  if (!context) throw new Error(errorText);

  return context;
}
