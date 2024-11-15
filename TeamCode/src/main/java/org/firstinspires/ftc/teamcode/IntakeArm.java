package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="IntakeArm")
public class IntakeArm extends OpMode
{
    public Servo iArmL, iArmR;
    public double iArmLMin, iArmLMax, iArmRMin, iArmRMax;
    public boolean iArmIsExtended;

    public boolean g1_a, g1_x, g1_b, g1_y, g1_l_bumper, g1_r_bumper;
    public boolean g2_a, g2_x, g2_b, g2_y, g2_l_bumper, g2_r_bumper;
    public boolean g1_a_last, g1_x_last, g1_b_last, g1_y_last, g1_l_bumper_last, g1_r_bumper_last;
    public boolean g2_a_last, g2_x_last, g2_b_last, g2_y_last, g2_l_bumper_last, g2_r_bumper_last;

    public double g1_l_stick_x, g1_l_stick_y, g1_r_stick_x, g1_r_stick_y;
    public double g2_l_stick_x, g2_l_stick_y, g2_r_stick_x, g2_r_stick_y;

    public void readControllerInput()
    {
        g1_a = gamepad1.a;
        g1_b = gamepad1.b;
        g1_x = gamepad1.x;
        g1_y = gamepad1.y;
        g1_l_bumper = gamepad1.left_bumper;
        g1_r_bumper = gamepad1.right_bumper;
        g2_a = gamepad2.a;
        g2_b = gamepad2.b;
        g2_x = gamepad2.x;
        g2_y = gamepad2.y;
        g2_l_bumper = gamepad2.left_bumper;
        g2_r_bumper = gamepad2.right_bumper;

        g1_l_stick_x = gamepad1.left_stick_x;
        g1_l_stick_y = gamepad1.left_stick_y;
        g1_r_stick_x = gamepad1.right_stick_x;
        g1_r_stick_y = gamepad1.right_stick_y;
        g2_l_stick_x = gamepad2.left_stick_x;
        g2_l_stick_y = gamepad2.left_stick_y;
        g2_r_stick_x = gamepad2.right_stick_x;
        g2_r_stick_y = gamepad2.right_stick_y;

    }

    public void assignLastInputValues()
    {
        g1_a_last = g1_a;
        g1_b_last = g1_b;
        g1_x_last = g1_x;
        g1_y_last = g1_y;
        g1_l_bumper_last = g1_l_bumper;
        g1_r_bumper_last = g1_r_bumper;
        g2_a_last = g2_a;
        g2_b_last = g2_b;
        g2_x_last = g2_x;
        g2_y_last = g2_y;
        g2_l_bumper_last = g2_l_bumper;
        g2_r_bumper_last = g2_r_bumper;
    }

    @Override
    public void init()
    {
        iArmL = hardwareMap.get(Servo.class, "iArmL");
        iArmR = hardwareMap.get(Servo.class, "iArmR");

        iArmLMin = 0.4;
        iArmLMax = 0.5;
        iArmRMin = 0.4;
        iArmRMax = 0.5;
        iArmIsExtended = false;

        iArmL.scaleRange(iArmLMin, iArmLMax);
        iArmR.scaleRange(iArmRMin, iArmRMax);
    }

    @Override
    public void start()
    {
        iArmL.setPosition(0);
        iArmR.setPosition(0);
    }

    @Override
    public void loop()
    {
        readControllerInput();

        if (ControllerUtils.justPressed(g2_b, g2_b_last))
        {
            if (iArmIsExtended)
            {
                iArmL.setPosition(0);
                iArmR.setPosition(0);
            }
            else
            {
                iArmL.setPosition(1);
                iArmR.setPosition(1);
            }
            iArmIsExtended = !iArmIsExtended;
        }

        assignLastInputValues();
    }
}
