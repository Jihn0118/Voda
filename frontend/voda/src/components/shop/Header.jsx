import React from "react";
import usePetStore from "../../store/petStore";
import styled from "styled-components";

const HeaderComponent = styled.div({
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
  width: "85%",
  borderBottom: "1px solid #ccc",
});

export default function Header() {
  const { currentCoin } = usePetStore();
  return (
    <HeaderComponent style={{ zIndex: 99 }}>
      <h3>상점</h3>
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          gap: "0.5rem",
        }}
      >
        <img
          src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Objects/Coin.png"
          alt="Coin"
          width="40rem"
          height="40rem"
        />
        <h3>{currentCoin}</h3>
      </div>
    </HeaderComponent>
  );
}
