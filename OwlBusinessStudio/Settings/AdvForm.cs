using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace OwlBusinessStudio.Settings
{
    public partial class AdvForm : Form
    {
        public AdvForm()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            //<script>
            //                    $(function(){
            //                        $("#slides").slidesjs({
            //                            width: 940,
            //                            height: 528,
            //                            navigation: true,
            //                            pagination: {
            //                                active: true,
            //                                // [boolean] Create pagination items.
            //                                // You cannot use your own pagination. Sorry.
            //                                effect: "slide"
            //                                // [string] Can be either "slide" or "fade".
            //                            },
            //                            play:
            //                            {
            //                                effect: "slide",
            //                                interval: 3000,
            //                                auto: true
            //                            }
            //                        });
            //                    });
            //                </script>
            //                <div id="slides">
            //                    <img src="http://placehold.it/940x528">
            //                    <img src="http://placehold.it/940x528">
            //                    <img src="http://placehold.it/940x528">
            //                    <img src="http://placehold.it/940x528">
            //                    <img src="http://placehold.it/940x528">
            //                    <img src="http://placehold.it/940x528">
            //                </div>
            StringBuilder builder_script = new StringBuilder("$(function(){$(\"#slides\").slidesjs({width: #w#,height: #h#,navigation: true,pagination: {active: true},play:{effect:\"#effect#\",interval:#interval#,auto:true}});});");
            StringBuilder builder_html = new StringBuilder("");
            builder_html.Append("<div id=\"slides\">");
            foreach (Control box in this.Controls)
            {
                if (box is TextBox && ((TextBox)box).Text != "")
                {
                    builder_html.Append("<img src=\"" + ((TextBox)box).Text + "\">");
                }
            }
            builder_html.Append("</div>");
            builder_script.Replace("#w#", NumWidth.Value.ToString());
            builder_script.Replace("#h#", NumHeight.Value.ToString());
            if (RadioSlide.Checked)
            {
                builder_script.Replace("#effect#", "slide");
            }
            else
            {
                builder_script.Replace("#effect#", "fade");
            }
            builder_script.Replace("#interval#", NumInterval.Value.ToString());
            MainForm.dbProc.setContent("banner", builder_html.ToString());
            MainForm.dbProc.setContent("banner_script", builder_script.ToString());
        }
    }
}
