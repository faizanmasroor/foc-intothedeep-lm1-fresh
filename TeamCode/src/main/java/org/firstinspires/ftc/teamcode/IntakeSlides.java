package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="IntakeSlides")
public class IntakeSlides extends OpMode
{
    public DcMotor iSlideL, iSlideR;
    public int I_SLIDE_MAX_POSITION;
    public double I_SLIDE_SPEED_MULTIPLIER;

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
        iSlideL = hardwareMap.get(DcMotor.class, "iSlideL");
        iSlideR = hardwareMap.get(DcMotor.class, "iSlideR");

        iSlideR.setDirection(DcMotorSimple.Direction.REVERSE);

        iSlideL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        iSlideR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        iSlideL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        iSlideR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        iSlideL.setPower(0);
        iSlideR.setPower(0);

        I_SLIDE_MAX_POSITION = 1100;
        I_SLIDE_SPEED_MULTIPLIER = 0.4;
    }

    @Override
    public void start()
    {

    }

    @Override
    public void loop()
    {
        readControllerInput();

        iSlideL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        iSlideR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // When joystick says retract but slides are fully retracted.
        if (g2_l_stick_y * -1 < 0 && (iSlideL.getCurrentPosition() <= 0 || iSlideR.getCurrentPosition() <= 0))
        {
            iSlideL.setPower(0);
            iSlideR.setPower(0);
        }
        // When joystick says extend but slides are fully extended.
        else if (g2_l_stick_y * -1 > 0 && (iSlideL.getCurrentPosition() >= I_SLIDE_MAX_POSITION || iSlideR.getCurrentPosition() >= I_SLIDE_MAX_POSITION))
        {
            iSlideL.setPower(0);
            iSlideR.setPower(0);
        }
        else
        {
            iSlideL.setPower(g2_l_stick_y * -1 * I_SLIDE_SPEED_MULTIPLIER);
            iSlideR.setPower(g2_l_stick_y * -1 * I_SLIDE_SPEED_MULTIPLIER);
        }

        assignLastInputValues();
    }
}
