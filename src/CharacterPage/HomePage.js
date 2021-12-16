import React from "react";
import PropTypes from "prop-types";
import { Grid } from "@mui/material";
import { border, Box, margin } from "@mui/system";
import { CenterFocusStrong, Home, Image } from "@mui/icons-material";

const HomePage = () => {
  return (
    <div>
      <Grid item xs={12}>
        <Box
          sx={{
            display: "block",
            position: "flex",
            border: "double",
            borderColor: "maroon",
            padding: "5px",
            margin: "10px",
            width: "flex",
            //height: "550px",
            //backgroundsize: "contain",
            //backgroundrepeat: "no-repeat",
            backgroundposition: "center",
          }}
        >
          <img
            src="http://img2.joyreactor.com/pics/post/full/art-CGI-dragon-3329461.jpeg"
            alt="Dragon"
            style={{ margin: "0 auto", display: "block" }}
            display="block"
            position="fixed"
            textalign="center"
            minheight="500px"
            minwidth="850px"
            height="800px"
            width="1275px"
            backgroundsize="contain"
            backgroundrepeat="no-repeat"
            backgroundposition="center"
            backgroundcolor="maroon"
            zindex="-3"
          ></img>
        </Box>
      </Grid>
    </div>
  );
};

HomePage.propTypes = {};

export default HomePage;
