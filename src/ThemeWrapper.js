import React from "react";
import App from "./App";
import { ThemeProvider, StyledEngineProvider } from "@mui/material/styles";
import { createTheme } from "@mui/material/styles";
import {
  RecoilRoot,
  atom,
  selector,
  useRecoilState,
  useRecoilValue,
} from "recoil";

const ThemeWrapper = (props) => {
  const theme = createTheme({
    palette: {
      primary: { main: "#9b111e" },
      secondary: {
        main: "#808080",
      },
    },
  });

  return (
    <RecoilRoot>
      <ThemeProvider theme={theme}>
        <App />
      </ThemeProvider>
    </RecoilRoot>
  );
};

export default ThemeWrapper;
