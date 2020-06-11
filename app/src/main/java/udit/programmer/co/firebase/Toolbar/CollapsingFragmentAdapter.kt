package udit.programmer.co.firebase.Toolbar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class CollapsingFragmentAdapter(fm: FragmentManager, var list: MutableList<QuestionFragment>) :
    FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = list[position]

    override fun getCount(): Int = list.size

    override fun getPageTitle(position: Int): CharSequence? {
        return "Fragment $position"
    }

}